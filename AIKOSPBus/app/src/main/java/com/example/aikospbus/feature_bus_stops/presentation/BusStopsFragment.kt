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

        viewModel.getRemoteBusStopsData(ApiConfig.cookie,"Afonso")

        viewModel.busDtoStopsDataModel.observe(viewLifecycleOwner) { busStopsData ->
            busStopsData?.forEach { stop ->
                val latLng = LatLng(stop.latitude, stop.longitude)
                addBusStopsOnMap(stop.nomeParada, latLng)
            }
        }

        return binding.root
    }

    private fun addBusStopsOnMap(name: String, location: LatLng) {
        mMap.setOnMapClickListener { 
            authentication()
        }

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

    private fun authentication() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    SPTransApi.retrofitService.authentication("604a216ace42329aa7581b9c6056a8a3dc2f574a680411928d5570478ca4c707")
                        .apply {
                            COOKIE = headers().get("Set-Cookie").toString()
                            val cookieHeader = headers().get("Set-Cookie") ?: ""
                            ApiConfig.cookie = cookieHeader
                            println("COOKIE: $COOKIE")
                        }

                if (response.isSuccessful) {
                    val result = response.body()
                    println("Login response: $result")
                } else {
                    println("Erro de autenticação: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                println("Erro HTTP: ${e.message()}")
            } catch (e: Exception) {
                println("Erro: ${e.message}")
            }
        }
    }
}