package com.example.aikospbus.feature_bus_stops.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.aikospbus.ApiConfig
import com.example.aikospbus.MainFragment.Companion.COOKIE
import com.example.aikospbus.R
import com.example.aikospbus.R.id.action_busStopsFragment_to_stopPredictionFragment
import com.example.aikospbus.R.id.action_mapsFragment_to_stopPredictionFragment
import com.example.aikospbus.common.custom_components.CustomHeader
import com.example.aikospbus.databinding.FragmentBusStopsBinding
import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

@AndroidEntryPoint
class BusStopsFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentBusStopsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance() = BusStopsFragment()
    }

    private val viewModel: BusStopsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusStopsBinding.inflate(inflater, container, false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setHeaderConfig()

        viewModel.getRemoteBusStopsData(ApiConfig.cookie,ApiConfig.searchStops)

        viewModel.busDtoStopsDataModel.observe(viewLifecycleOwner) { busStopsData ->
            busStopsData?.forEach { stop ->
                val latLng = LatLng(stop.latitude, stop.longitude)
                addBusStopsOnMap(stop.nomeParada, latLng)
            }
        }

        return binding.root
    }

    private fun addBusStopsOnMap(name: String, location: LatLng) {

        val sp = LatLng(location.latitude, location.longitude)
        mMap.addMarker(MarkerOptions().position(sp).title(name))
        mMap.setOnMarkerClickListener { marker ->
            findNavController().navigate(action_busStopsFragment_to_stopPredictionFragment)
            true
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sp = LatLng(-23.5489, -46.6388)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sp))
        val cameraUpdate = CameraUpdateFactory.zoomTo(10f)
        mMap.moveCamera(cameraUpdate)
    }

    private fun setHeaderConfig() {
        val customHeader = binding.customHeader

        customHeader.setConfig(object : CustomHeader.HeaderClickListener {
            override fun setBackButtonClickListener() {
                findNavController().popBackStack()
            }
        }, title = "Mapa")
    }

}