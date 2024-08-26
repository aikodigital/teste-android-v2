package com.example.aikospbus.feature_bus_location.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aikospbus.ApiConfig
import com.example.aikospbus.R
import com.example.aikospbus.databinding.FragmentBusLocationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusLocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentBusLocationBinding? = null
    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance() = BusLocationFragment()
    }

    private val viewModel: BusLocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusLocationBinding.inflate(inflater, container, false)

//        val busLocationObject = BusLocation(
//            id = 0,
//            word = "Teste",
//        )
//
//        viewModel.insertBusLocation(busLocationObject)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB ${ApiConfig.searchLocationLine}")
        viewModel.getRemoteBusLocationData(ApiConfig.cookie,ApiConfig.searchLocationLine)

        viewModel.busDtoLocationDataModel.observe(viewLifecycleOwner) { busLocationData ->
                busLocationData?.vehicleDtos?.forEach { vehicle ->
                    val latLng = LatLng(vehicle.latitude, vehicle.longitude)
                    addBusLocationOnMap(vehicle.prefixo.toString(), latLng)
                }
        }

        return binding.root
    }

    private fun addBusLocationOnMap(name: String, location: LatLng) {
        val sp = LatLng(location.latitude, location.longitude)
        mMap.addMarker(MarkerOptions().position(sp).title(name))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sp = LatLng(-23.5489, -46.6388)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sp))
        val cameraUpdate = CameraUpdateFactory.zoomTo(10f)
        mMap.moveCamera(cameraUpdate)
    }
}