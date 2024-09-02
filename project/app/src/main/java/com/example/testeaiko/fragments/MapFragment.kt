package com.example.testeaiko.fragments

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testeaiko.R
import com.example.testeaiko.SessionManager
import com.example.testeaiko.adapters.LinesAdapter
import com.example.testeaiko.adapters.SearchFilterAdapter
import com.example.testeaiko.adapters.VehicleAdapter
import com.example.testeaiko.databinding.FragmentMapBinding
import com.example.testeaiko.datamodels.ModelParada
import com.example.testeaiko.datamodels.ModelPosicao
import com.example.testeaiko.datamodels.ModelVeiculo
import com.example.testeaiko.util.MarkerHelper
import com.example.testeaiko.viewmodels.MapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentMapBinding
    private val viewModel: MapViewModel by viewModels()
    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private var positions: ModelPosicao? = null
    private var stops: List<ModelParada>? = null
    private lateinit var markerHelper: MarkerHelper
    private var isUserInteracting = false
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 5000L // 5 seconds
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var filterAdapter: SearchFilterAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lastStop: LatLng? = null
    lateinit var sessionManager: SessionManager
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_map, container, false
        )

        setupSearchView()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        binding.btnLocation.setOnClickListener {
            moveToCurrentLocation()
        }
        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        markerHelper = MarkerHelper(requireContext())
        viewModel.resetTransitData()
        initializeInfoSheet(view)
        setupObservers()
        startPositionUpdates()
        fetchStops()
    }

    private fun initializeInfoSheet(view: View) {
        val bottomSheet: FrameLayout = view.findViewById(R.id.bottomSheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.isHideable = false
    }

    private fun setupObservers() {
        setupVehicleObserver()
        setupStopsObserver()
        setupRoutesObserver()
        setupArrivalsObserver()
        setupLinesSearchObserver()
    }

    private fun setupLinesSearchObserver() {
        viewModel.lineSearchInfo.observe(viewLifecycleOwner) { result ->
            result?.onSuccess { response ->
                if (response.isSuccessful) {
                    val lines = response.body() ?: emptyList()
                    if (lines.isNotEmpty()) {
                        binding.bottomSheet.rvInfo.adapter = LinesAdapter(lines)
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                        binding.bottomSheet.ivEmpty.visibility = View.GONE
                    } else {
                        binding.bottomSheet.rvInfo.adapter = null
                        binding.bottomSheet.ivEmpty.visibility = View.VISIBLE
                        handleEmptyResult()

                    }
                } else {
                    handleErrorCode(response.code())
                }
            }
        }
    }

    private fun setupArrivalsObserver() {
        viewModel.stopArrivalsInfo.observe(viewLifecycleOwner) { result ->
            result?.onSuccess { response ->
                if (response.isSuccessful) {
                    val stop = response.body()?.p
                    val lines = stop?.l ?: emptyList()
                    val vehicles =
                        if (lines.isNotEmpty()) lines.flatMap { it.vs }.map { it } else emptyList()
                    if (stop != null) {
                        lastStop = LatLng(stop.py, stop.px)
                    }
                    if (vehicles.isEmpty()) {
                        binding.bottomSheet.rvInfo.adapter = null
                        binding.bottomSheet.ivEmpty.visibility = View.VISIBLE
                        handleEmptyResult()
                    } else {
                        binding.bottomSheet.rvInfo.adapter =
                            VehicleAdapter(vehicles) { modelVeiculo ->
                                fetchRoute(modelVeiculo)
                            }
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                        binding.bottomSheet.ivEmpty.visibility = View.GONE
                    }
                } else {
                    handleErrorCode(response.code())
                }
            }
        }
    }

    private fun fetchRoute(modelVeiculo: ModelVeiculo) {
        if (lastStop != null) {
            val vehicleLocation = LatLng(modelVeiculo.py, modelVeiculo.px)
            val origin = "${lastStop!!.latitude},${lastStop!!.longitude}"
            val destination = "${vehicleLocation.latitude},${vehicleLocation.longitude}"
            viewModel.getRoutes(origin, destination)
        }
    }

    private fun setupRoutesObserver() {
        viewModel.routesInfo.observe(viewLifecycleOwner) { result ->
            result?.onSuccess { response ->
                if (response.isSuccessful) {
                    val route = response.body()
                    if (route != null) {
                        val points = route.routes.firstOrNull()?.overview_polyline?.points
                        if (points != null) {
                            markerHelper.drawRoute(mMap, points)
                            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                } else {
                    handleErrorCode(response.code())
                }
            }
        }
    }

    private fun setupStopsObserver() {
        viewModel.stopsInfo.observe(viewLifecycleOwner) { result ->
            result?.onSuccess { response ->
                if (response.isSuccessful) {
                    stops = response.body()
                } else {
                    handleErrorCode(response.code())
                }
            }
        }
    }

    private fun setupVehicleObserver() {
        viewModel.vehicleInfo.observe(viewLifecycleOwner) { result ->
            result?.onSuccess { response ->
                if (response.isSuccessful) {
                    positions = response.body()
                    if (!isUserInteracting) {
                        updateMarkers()
                    }
                } else {
                    handleErrorCode(response.code())
                }
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
            binding.searchView.requestFocusFromTouch()
        }
        val searchEditText =
            binding.searchView.findViewById<EditText>(com.google.android.material.R.id.search_src_text)
        val filter = InputFilter { source, _, _, _, _, _ ->
            source?.filter { it.isLetterOrDigit() || it.isWhitespace() }
        }
        searchEditText.filters = arrayOf(filter)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    when (filterAdapter.getSelecteditem()) {
                        0 -> viewModel.searchStop(query.toInt())
                        1 -> viewModel.searchLine(query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        setupSearchFilters()
    }

    private fun setupSearchFilters() {
        val searchTargets = listOf("Parada", "Linha")
        filterAdapter = SearchFilterAdapter(searchTargets) {}
        binding.rvSearchFilters.adapter = filterAdapter
    }

    private fun fetchMarkerInfo(markerTitle: String) {
        viewModel.searchStop(markerTitle.toInt())
    }

    private fun handleErrorCode(code: Int) {
        when (code) {
            401 -> sessionManager.handleAuthenticationFailure()
            else -> {
                // Handle other errors
                Toast.makeText(
                    requireContext(),
                    "Erro ao buscar informações, código ${code}.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleEmptyResult() {
        Toast.makeText(requireContext(), "Sem resultados...", Toast.LENGTH_SHORT).show()
    }

    private fun startPositionUpdates() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                fetchPositions()
                handler.postDelayed(this, updateInterval)
            }
        }, updateInterval)
    }

    private fun fetchPositions() {
        viewModel.getTransitInfo()

    }

    private fun fetchStops() {
        viewModel.getStopsInfo()
    }

    private fun updateMarkers() {
        if (positions == null) {
            return
        }
        markerHelper.removeAllMarkers()

        // Get the current visible region (bounds) of the map and its center
        val bounds = mMap.projection.visibleRegion.latLngBounds
        val mapCenter = bounds.center
        // Define a maximum radius (in meters) from the center of the visible region
        val maxRadius = 2000
        if (stops != null) {
            for (stop in stops!!) {
                val latLng = LatLng(stop.py, stop.px)
                if (bounds.contains(latLng) && isWithinRadius(mapCenter, latLng, maxRadius)) {
                    markerHelper.addStopIconMarker(mMap,
                        latLng,
                        stop.cp.toString(),
                        .8f)
                }
            }
        }
        for (linha in positions!!.l) {
            for (veiculo in linha.vs) {
                val latLng = LatLng(veiculo.py, veiculo.px)
                if (bounds.contains(latLng) && isWithinRadius(mapCenter, latLng, maxRadius)) {
                    markerHelper.addVehicleMarker(mMap,
                        latLng,
                        veiculo.p.toString(),
                        .8f)
                }
            }
        }

    }

    private fun isWithinRadius(center: LatLng, point: LatLng, radius: Int): Boolean {
        val results = FloatArray(1)
        Location.distanceBetween(
            center.latitude, center.longitude,
            point.latitude, point.longitude,
            results
        )
        return results[0] <= radius
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        enableMyLocation()
        mMap.setOnCameraIdleListener {
            isUserInteracting = false
            updateMarkers()
        }
        googleMap.setOnMarkerClickListener { marker ->
            // Show the bottom sheet with marker info
            markerHelper.isStopMarker(marker).let {
                if (it) {
                    marker.title?.let { it1 -> fetchMarkerInfo(it1) }
                    lastStop = marker.position
                }
            }
            true
        }
        mMap.setOnCameraMoveStartedListener { isUserInteracting = true }
        // Set up the map to center on São Paulo , I live in another state and emulator's location was not working
        val saoPaulo = LatLng(-23.5505, -46.6333) // Latitude and Longitude of São Paulo
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(saoPaulo, 15f)) // Zoom level 12
    }

    private fun enableMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    private fun moveToCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                location?.let {
                    val currentLocation = LatLng(it.latitude, it.longitude)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f))
                }
            }
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    // MapView lifecycle methods
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}