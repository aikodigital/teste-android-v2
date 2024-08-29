package br.com.aiko.projetoolhovivo.ui.position.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import br.com.aiko.projetoolhovivo.R
import br.com.aiko.projetoolhovivo.data.model.stop.Stop
import br.com.aiko.projetoolhovivo.data.model.vehicle.Vehicle
import br.com.aiko.projetoolhovivo.databinding.MapPositionFragmentBinding
import br.com.aiko.projetoolhovivo.ui.forecast.view.ForecastDetailsActivity
import br.com.aiko.projetoolhovivo.ui.line.dialog.LineDialog
import br.com.aiko.projetoolhovivo.ui.position.PositionMap
import br.com.aiko.projetoolhovivo.ui.position.PositionMapViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.DaggerFragment

class MapPositionFragment : DaggerFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    lateinit var viewModel: PositionMapViewModel

    private var vehicles: ArrayList<Vehicle> = arrayListOf()
    private var stops: ArrayList<Stop> = arrayListOf()

    private lateinit var binding: MapPositionFragmentBinding
    private var initMap = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MapPositionFragmentBinding.inflate(inflater, container, false);
        return binding.root
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity())[PositionMapViewModel::class.java]

        viewModel.positionMap.observe(viewLifecycleOwner, this::updatePositionMap)
        viewModel.isLoading.observe(viewLifecycleOwner, this::updateLoading)
        viewModel.error.observe(viewLifecycleOwner) { error ->
            run {
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.getPositionByListLines()
    }

    private fun updateLoading(isLoading: Boolean) {
        binding.pbLoadingMapMain.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun updatePositionMap(positionMap: PositionMap?) {
        if (positionMap != null) {
            binding.tvLastUpdateMain.text =
                getString(R.string.til_last_update_position).plus(" ").plus(positionMap.lastUpdate)
            this.vehicles.clear()
            this.vehicles.addAll(positionMap.vehicles)
            this.stops.clear()
            this.stops.addAll(positionMap.stops)
            if (initMap) onMapReady(mMap)
        }
    }

    private fun initialView() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapMain) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.ivFilterMyLocation.setOnClickListener {
            if (initMap) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_PERMISSION_REQUEST_CODE
                    )
                }

                mMap.isMyLocationEnabled = true
                fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val userLocation = LatLng(location.latitude, location.longitude)
                        mMap.addMarker(
                            MarkerOptions().position(userLocation).title("Sua Localização")
                        )
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                    }
                }
            }
        }

        binding.tileLineFilterMap.editText?.setOnClickListener {
            LineDialog(requireActivity()) { positionLine ->
                binding.tileLineFilterMap.editText?.setText(positionLine.sign)
                viewModel.getVehiclesPositionByCodeLine(positionLine.code)
            }.show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (!initMap) initMap = true
        mMap.clear()
        val iconVehicle = BitmapDescriptorFactory.fromResource(R.drawable.pin_blue)
        val iconStop = BitmapDescriptorFactory.fromResource(R.drawable.pin_red)
        val place = LatLng(-23.522339, -46.696246)
        for (stop in stops) {
            val position = mMap.addMarker(
                MarkerOptions().position(LatLng(stop.latitude, stop.longitude))
                    .title(stop.name)
                    .icon(iconStop)
            )
            position?.tag = "stop_${stop.code}"
        }
        for (vehicle in vehicles) {
            val position = mMap.addMarker(
                MarkerOptions().position(LatLng(vehicle.latitude, vehicle.longitude))
                    .title(vehicle.prefixVehicle.toString()).icon(iconVehicle)
            )
            position?.tag = "vehicle_${vehicle.prefixVehicle}"
        }
        mMap.setOnMarkerClickListener(this)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place, 14f))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onMapReady(mMap)
            }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val splitTag = marker.tag.toString().split("_")
        if (splitTag.size > 1 && splitTag[0] == "stop") {
            val stopFilter = this.stops.filter { st -> st.code == splitTag[1].toInt() }
            if (stopFilter.isNotEmpty()) ForecastDetailsActivity.startActivity(
                requireContext(),
                stopFilter[0]
            )
        }
        return true
    }
}