package com.example.aikospbus.feature_bus_location.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.aikospbus.ApiConfig
import com.example.aikospbus.R
import com.example.aikospbus.common.custom_components.CustomHeader
import com.example.aikospbus.databinding.FragmentBusLocationBinding
import com.example.aikospbus.feature_api_sp_trans.remote.api.CookieManager
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

@AndroidEntryPoint
class BusLocationFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentBusLocationBinding? = null
    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap

    companion object {
        fun newInstance() = BusLocationFragment()
    }

    private val viewModel: BusLocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusLocationBinding.inflate(inflater, container, false)

        setHeaderConfig()

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        handleApiCookies()

        binding.errorLayoutBt.setOnClickListener {
            binding.mapLayout.visibility = View.VISIBLE
            binding.errorLayout.visibility = View.GONE
        }

        viewModel.hasVehicleData.observe(viewLifecycleOwner) {
            handleScreenLayout(it)
        }

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
//        mMap.addMarker(MarkerOptions().position(sp).title(name).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)))
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

    private fun handleApiCookies() {
        CoroutineScope(Dispatchers.Main).launch {
            if (CookieManager.isCookieValid()) {
                viewModel.getRemoteBusLocationData(
                    CookieManager.cookie,
                    ApiConfig.searchLocationLine
                )
            } else {
                CookieManager.authentication()
                viewModel.getRemoteBusLocationData(
                    CookieManager.cookie,
                    ApiConfig.searchLocationLine
                )
            }
        }
    }

    private fun handleScreenLayout(apiStatus: String) {
        when (apiStatus) {
            "Success" -> {
                binding.mapLayout.visibility = View.VISIBLE
                binding.errorLayout.visibility = View.GONE
            }

            "Error" -> {
                binding.mapLayout.visibility = View.GONE
                binding.errorLayout.visibility = View.VISIBLE
            }
        }
    }
}