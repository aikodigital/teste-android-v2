package com.matreis.teste.sptrans.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.databinding.FragmentMapBinding
import com.matreis.teste.sptrans.helper.BitmapHelper
import com.matreis.teste.sptrans.presentation.dialog.DialogInfoBusStop
import com.matreis.teste.sptrans.viewmodels.MapViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var map: GoogleMap
    private val mapViewModel: MapViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isZoomGesturesEnabled = true
        map.uiSettings.isScrollGesturesEnabled = true
        map.uiSettings.isRotateGesturesEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true
        map.setOnMarkerClickListener { marker ->
            val busStop = mapViewModel.busStops.value?.firstOrNull { it.name == marker.title }
            val dialog = DialogInfoBusStop(
                seeTimes = {
                },
                defineRoute = {
                }
            )
            busStop?.let {
                dialog.setBusStop(it)
                dialog.show(childFragmentManager, "dialog")
            }
            true
        }
        initObservers()
    }

    private fun initObservers() {
        mapViewModel.busStops.observe(viewLifecycleOwner) {
            val location = CameraUpdateFactory.newLatLngZoom(
                LatLng(it[0].lat!!, it[0].lng!!), 15f
            )
            map.animateCamera(location)
            it.forEach { busStop ->
                val latLng = LatLng(busStop.lat!!, busStop.lng!!)
                map.addMarker(
                    MarkerOptions().position(latLng).title(busStop.name).icon(
                        BitmapHelper.vectorToBitmap(
                            requireContext(),
                            R.drawable.bus_stop,
                            ContextCompat.getColor(requireActivity(), R.color.md_theme_error)
                        )
                    )
                )
            }
        }

        mapViewModel.vehiclePositions.observe(viewLifecycleOwner) {
            it.forEach { vehicle ->
                val latLng = LatLng(vehicle.lat!!, vehicle.lng!!)
                map.addMarker(
                    MarkerOptions().position(latLng).title(vehicle.prefix).icon(
                        BitmapHelper.vectorToBitmap(
                            requireContext(),
                            R.drawable.baseline_directions_bus_24,
                            ContextCompat.getColor(requireActivity(), R.color.primary)
                        )
                    )
                )
            }
        }
    }

}