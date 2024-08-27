package com.example.myapplication.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.LineAdapter
import com.example.myapplication.adapter.ListItem
import com.example.myapplication.databinding.FragmentMapBinding
import com.example.myapplication.model.BusLineVehicle
import com.example.myapplication.model.BusStop
import com.example.myapplication.model.BusStopSearch
import com.example.myapplication.viewModel.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import android.location.Geocoder
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.adapter.BusAdapter
import com.example.myapplication.infra.Token
import com.example.myapplication.viewModel.LineViewModel
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.withContext


class MapFragment : Fragment() {
    companion object {
        fun newInstance(): MapFragment {
            return MapFragment()
        }
    }

    private lateinit var binding: FragmentMapBinding
    private lateinit var mMap: GoogleMap
    private lateinit var editText: EditText
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private val viewModel: MapViewModel by viewModels()
    private val viewModelLine: LineViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    private var num = 0
    private var ruaIndex = 0
    private var charIndex = 0
    private lateinit var icon: BitmapDescriptor
    private val busMarkers = mutableMapOf<String, Marker>()
    private var busLineVehicle: MutableList<BusLineVehicle> = mutableListOf()
    private var busStopList: List<BusStop> = emptyList()
    private lateinit var busAdapter: BusAdapter
    private lateinit var lineAdapter: LineAdapter
    private var stopList: List<BusStopSearch> = emptyList()

    private val ruas = listOf(
        "Avenida Paulista",
        "Rua Oscar Freire",
        "Avenida Ibirapuera",
        "Rua 25 de Março"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Chame a função suspend dentro de uma coroutine
        viewLifecycleOwner.lifecycleScope.launch {
            initializeAdapters()
        }

        configureBottomSheet()
        setupMap()
        setupHintUpdate()
        initSearchContainer()
        observeViewModel()

        // Inicialize e configure o RecyclerView para busAdapter
        busAdapter = BusAdapter(requireContext())
        binding.rv.apply {
            adapter = busAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Observa as mudanças no ViewModel e atualiza o busAdapter
        viewModelLine.busStops.observe(viewLifecycleOwner) {
            busAdapter.submitList(busLineVehicle)
        }

        // Configurar o listener para dimBackground
        binding.dimBackground.setOnClickListener {
            hideOptionsContainer()  // Oculta o modal e limpa o fundo
        }

        // Configurar o Floating Action Button
        binding.fab.setOnClickListener {
            //  fetchAndDisplayBusStops(34041)
        }

        // Inicializar e configurar o RecyclerView para lineAdapter
        recyclerView = binding.rvLines
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = lineAdapter
        }

        // Configurar o LineAdapter
        lineAdapter = LineAdapter(requireContext()) { listItem ->
            // Ação a ser executada quando um item for clicado
            val lineCode = when (listItem) {
                is ListItem.BusLine -> listItem.vehicle.line.lineCode
                is ListItem.Line -> listItem.line.lineCode
            }
            val lineCodeInt = lineCode.toIntOrNull()
            if (lineCodeInt != null) {
                (parentFragment as? MapFragment)?.let { fragment ->
                    viewLifecycleOwner.lifecycleScope.launch {
                        fragment.updateMapWithLineCode(lineCodeInt)
                    }
                }
            }
        }

        // Certifique-se de que o adapter está definido para o RecyclerView
        recyclerView.adapter = lineAdapter
    }


    private fun showOptionsContainer() {
        binding.dimBackground.visibility = View.VISIBLE
        binding.optionsContainer.visibility = View.VISIBLE
        binding.dimBackground.alpha = 0f
        binding.optionsContainer.alpha = 0f
        binding.dimBackground.animate().alpha(0.5f).setDuration(200)
        binding.optionsContainer.animate().alpha(1f).setDuration(200)
    }

    private fun hideOptionsContainer() {
        binding.dimBackground.animate().alpha(0f).setDuration(200).withEndAction {
            binding.dimBackground.visibility = View.GONE
        }
        binding.optionsContainer.animate().alpha(0f).setDuration(200).withEndAction {
            binding.optionsContainer.visibility = View.GONE
        }
    }

    fun initializeAdapters() {
        lineAdapter = LineAdapter(requireContext(), mutableListOf()) { listItem ->
            val lineCode = when (listItem) {
                is ListItem.BusLine -> listItem.vehicle.line.lineCode
                is ListItem.Line -> listItem.line.lineCode
            }

            // Converta o lineCode para Int
            val lineCodeInt = lineCode.toIntOrNull() ?: run {
                // Caso a conversão falhe, mostre um erro ou trate a falha
                Toast.makeText(requireContext(), "Código da linha inválido", Toast.LENGTH_SHORT)
                    .show()
                return@LineAdapter
            }

            // Inicia uma coroutine para chamar a função suspend
            viewLifecycleOwner.lifecycleScope.launch {
                (parentFragment as? MapFragment)?.updateMapWithLineCode(lineCodeInt)
            }
        }
    }

    private fun setupMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync { googleMap ->
            mMap = googleMap
            initializeMap()
            fetchBusStop()
            setupMapListeners()
        }
    }

    private fun initializeMap() {
        // Coordenadas do Terminal de Ônibus da Barra Funda
        val barraFunda = LatLng(-23.534426, -46.638737)

        mMap.addMarker(
            MarkerOptions()
                .position(barraFunda)
                .title("Terminal de Ônibus da Barra Funda")
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barraFunda, 15f))
    }

    @SuppressLint("PotentialBehaviorOverride")
    private fun setupMapListeners() {
        mMap.setOnMapClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        mMap.setOnMarkerClickListener { clickedMarker ->
            handleMarkerClick(clickedMarker)
            true
        }
    }

    private fun handleMarkerClick(clickedMarker: Marker) {
        val clickedBusStop = clickedMarker.getBusStop()
        val busInfo = clickedMarker.getBusMarker()

        clickedBusStop?.let {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.fetchBusArriveTime(it.stopCode)
            }
            updateUIForBusStop(it)
        }

        busInfo?.let {
            showBusDialog(it)
        }
    }


    private fun updateUIForBusStop(busStop: BusStop) {
        configureRecyclerView(busAdapter, binding.rv)
        configureRecyclerView(lineAdapter, binding.rvLines)

        val stopNameText = getString(R.string.bus_stop_name, busStop.stopName)
        val stopAddressText = getString(R.string.bus_stop_address, busStop.stopLocation)

        binding.busStopName.text = stopNameText
        binding.busStopAdress.text = stopAddressText
        configureClickListeners(busStop)
        num = 0
    }

    private fun configureRecyclerView(
        adapter: RecyclerView.Adapter<*>,
        recyclerView: RecyclerView
    ) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun fetchBusStop() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                busStopList = viewModel.getBusStop()
                activity?.runOnUiThread {
                    findBusStop(busStopList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun findBusStop(busStopList: List<BusStop>) {
        icon = imageToBitmapDescriptor(R.drawable.busspoint)

        busStopList.forEach { busStop ->
            val marker = mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(busStop.lat, busStop.long))
                    .title(busStop.stopLocation)
                    .icon(icon)
            )
            marker?.setBusStop(busStop)
        }
    }

    private fun imageToBitmapDescriptor(image: Int): BitmapDescriptor {
        val bitmap = BitmapFactory.decodeResource(resources, image)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false)
        return BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    }

    private fun showBusInTheMap() {
        val icon = imageToBitmapDescriptor(R.drawable.bus2)

        busLineVehicle.forEach { bl ->
            val position = LatLng(bl.vehicle.lat, bl.vehicle.long)
            val busPrefix = bl.vehicle.busPrefix

            if (busMarkers.containsKey(busPrefix)) {
                busMarkers[busPrefix]?.position = position
            } else {
                val busInfo = mMap.addMarker(
                    MarkerOptions()
                        .position(position)
                        .title("Bus Prefix: $busPrefix")
                        .snippet("Additional Info: ${bl.vehicle.arriveTime}")
                        .icon(icon)
                )
                busInfo?.let {
                    busMarkers[busPrefix] = it
                    it.setBusMarker(bl)
                }
            }
        }
    }

    private fun showBusDialog(busInfo: BusLineVehicle) {
        AlertDialog.Builder(requireContext())
            .setTitle("Informações do ônibus")
            .setMessage(
                "Letreiro: ${busInfo.line.lineSign}\n" +
                        "Código da linha: ${busInfo.line.lineCode}\n" +
                        "Prefixo: ${busInfo.vehicle.busPrefix}\n" +
                        "Previsão de chegada: ${busInfo.vehicle.arriveTime}\n" +
                        "Destino: ${busInfo.line.destination}"
            )
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun configureClickListeners(busStop: BusStop) {
        showOptionsContainer()
        binding.busTime.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.fetchBusArriveTime(busStop.stopCode)
            }
            configureRecyclerView(busAdapter, binding.rv)
            binding.rvLines.visibility = View.GONE
            binding.rv.visibility = View.VISIBLE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.linesRoute.setOnClickListener {
            showOptionsContainer()
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.fetchBusArriveTime(busStop.stopCode)
            }
            configureRecyclerView(lineAdapter, binding.rvLines)
            binding.rv.visibility = View.GONE
            binding.rvLines.visibility = View.VISIBLE
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        binding.fab.setOnClickListener {
            busMarkers.values.forEach { it.remove() }
            busMarkers.clear()
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.fetchBusArriveTime(busStop.stopCode)
            }
            showBusInTheMap()
            Toast.makeText(requireContext(), "Apresentação veículos próximos onde estavam na sua última atualização", Toast.LENGTH_SHORT).show()
        }
    }
    private fun imageToBitMap(image: Int): BitmapDescriptor {
        val bitmap = BitmapFactory.decodeResource(resources, image)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false)
        icon = BitmapDescriptorFactory.fromBitmap(scaledBitmap)
        return icon
    }

    private fun setupHintUpdate() {
        editText = binding.editTextText
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val currentRua = ruas[ruaIndex]
                if (charIndex < currentRua.length) {
                    editText.hint = currentRua.substring(0, charIndex + 1)
                    charIndex++
                    handler.postDelayed(this, 300)
                } else {
                    charIndex = 0
                    ruaIndex = (ruaIndex + 1) % ruas.size
                    handler.postDelayed(this, 1000)
                }
            }
        }
        handler.post(runnable)
    }

    private fun configureBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomsheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.peekHeight = 0
        binding.dimBackground.setOnClickListener {
            hideOptionsContainer()
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun initSearchContainer() {
        binding.button.setOnClickListener {
            val searchText = binding.editTextText.text.toString()
            if (searchText.isNotEmpty()) {
                search(searchText)
            }
        }
    }

    private fun search(query: String) {
        when (binding.searchTypeGroup.checkedRadioButtonId) {
            R.id.radio_address -> searchLocation(query)
            R.id.radio_bus_stop -> searchBusStop(query)
        }
    }

    private fun searchLocation(address: String) {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val addressList = geocoder.getFromLocationName(address, 1)
                if (addressList?.isNotEmpty() == true) {
                    val location = addressList[0]
                    val latLng = LatLng(location.latitude, location.longitude)
                    withContext(Dispatchers.Main) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
                        mMap.addMarker(MarkerOptions().position(latLng).title(address))
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "Endereço não encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Erro ao buscar endereço", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun searchBusStop(busStopName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val authToken = Token
                val busStopList = viewModel.apiService.getBusStopByNameOrAddress(
                    authToken.toString(),
                    busStopName
                )

                if (busStopList.isNotEmpty()) {
                    val busStop = busStopList[0]
                    val latLng = LatLng(busStop.lat, busStop.long)
                    withContext(Dispatchers.Main) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
                        mMap.addMarker(MarkerOptions().position(latLng).title(busStop.stopName))
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "Ponto de ônibus não encontrado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Erro de conexão", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun observeViewModel() {
        viewModel.listOfBus.observe(viewLifecycleOwner, Observer { busList ->
            busAdapter.submitList(busList)
            val filterLine = busList.distinctBy { it.line.lineSign }
            val lineList = filterLine.map { ListItem.BusLine(it) }
            lineAdapter.setData(lineList)
            busLineVehicle = busList
        })
    }

    suspend fun updateMapWithLineCode(lineCode: Int) {
        fetchAndDisplayBusStops(lineCode)
    }

    private fun fetchAndDisplayBusStops(lineCode: Int) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            try {
                val busStops = viewModel.apiService.getStopsByLine(lineCode)
                withContext(Dispatchers.Main) {
                    displayBusStopsOnMap(busStops)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Erro ao carregar paradas", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun displayBusStopsOnMap(busStops: List<BusStop>) {
        val polylineOptions = PolylineOptions().apply {
            color(Color.BLUE)
            width(10f)
        }


        busStops.forEach { busStop ->
            val position = LatLng(busStop.lat, busStop.long)
            polylineOptions.add(position)
        }

        if (polylineOptions.points.isNotEmpty()) {
            mMap.addPolyline(polylineOptions)
        }

        if (busStops.isNotEmpty()) {
            val firstStop = busStops[0]
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(firstStop.lat, firstStop.long),
                    12f
                )
            )
        }
    }

    private fun Marker.setBusStop(busStop: BusStop) {
        tag = busStop
    }

    private fun Marker.getBusStop(): BusStop? {
        return tag as? BusStop
    }

    private fun Marker.setBusMarker(busStop: BusLineVehicle) {
        tag = busStop
    }

    private fun Marker.getBusMarker(): BusLineVehicle? {
        return tag as? BusLineVehicle
    }
}