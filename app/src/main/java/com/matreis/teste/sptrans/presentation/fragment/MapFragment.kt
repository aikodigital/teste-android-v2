package com.matreis.teste.sptrans.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.databinding.FragmentMapBinding
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.Line
import com.matreis.teste.sptrans.helper.BitmapHelper
import com.matreis.teste.sptrans.helper.customGetSerializable
import com.matreis.teste.sptrans.helper.getDirection
import com.matreis.teste.sptrans.helper.orElse
import com.matreis.teste.sptrans.presentation.dialog.DialogInfoBusStop
import com.matreis.teste.sptrans.viewmodels.MapViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentMapBinding
    private lateinit var map: GoogleMap
    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null) {
            val line = arguments?.customGetSerializable<Line>("line")
            line?.let {

                mapViewModel.setSelectedLine(it)
                mapViewModel.getLinesInformation(it.codLine!!)
            }
        }
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
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        mapViewModel.selectedLine.observe(viewLifecycleOwner) {
            binding.tvLineDescription.text = it.getDirection()
        }
    }

    private fun initListeners() {
        binding.materialButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    @SuppressLint("PotentialBehaviorOverride")
    override fun onMapReady(p0: GoogleMap) {
        map = p0
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isZoomGesturesEnabled = true
        map.uiSettings.isScrollGesturesEnabled = true
        map.uiSettings.isRotateGesturesEnabled = true
        map.uiSettings.isMyLocationButtonEnabled = true
        //mapViewModel.getRoadSpeed(requireContext(), map)
        val location = CameraUpdateFactory.newLatLngZoom(
            LatLng(-23.555883, -46.66306), 10f
        )
        map.animateCamera(location)
        map.setOnMarkerClickListener { marker ->
            showMarkerInfo(marker)
            true
        }
        initMapsInformationObservers()
    }

    private fun showMarkerInfo(marker: Marker) {
        val busStop = mapViewModel.getBusStopList().firstOrNull { it.name == marker.title }
        val dialog = DialogInfoBusStop(
            seeTimes = {
                safeNavigateToBusStopFragment(it)
            },
            defineRoute = {
            }
        )
        busStop?.let {
            dialog.setBusStop(it)
            dialog.show(childFragmentManager, "dialog")
        }.orElse {
            marker.showInfoWindow()
        }
    }

    private fun safeNavigateToBusStopFragment(busStop: BusStop) {
        val bundle = Bundle()
        bundle.putLong("stopCod", busStop.stopCod!!)
        bundle.putLong("lineCod", mapViewModel.selectedLine.value!!.codLine!!)
        findNavController().navigate(R.id.action_mapFragment_to_busStopTimesFragment, bundle)
    }

    private fun initMapsInformationObservers() {
        mapViewModel.markers.observe(viewLifecycleOwner) { markers ->
            map.clear()
            markers.busStops.forEach { busStop ->
                val latLng = LatLng(busStop.lat!!, busStop.lng!!)
                map.addMarker(
                    MarkerOptions().position(latLng).title(busStop.name).icon(
                        BitmapHelper.vectorToBitmap(
                            requireContext(),
                            R.drawable.pin_bus_stop,
                            ContextCompat.getColor(requireActivity(), R.color.md_theme_error)
                        )
                    )
                )
            }
            markers.vehicles.forEach { vehicle ->
                val latLng = LatLng(vehicle.lat!!, vehicle.lng!!)
                map.addMarker(
                    MarkerOptions().position(latLng).title(vehicle.prefix).icon(
                        BitmapHelper.vectorToBitmap(
                            requireContext(),
                            R.drawable.pin_bus,
                            ContextCompat.getColor(requireActivity(), R.color.primary)
                        )
                    )
                )
            }
        }
        mapViewModel.error.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { error ->
                Snackbar.make(binding.root, getString(error), Snackbar.LENGTH_LONG).show()
            }
        }
       /* mapViewModel.kmlLayer.observe(viewLifecycleOwner) { kmlLayer ->
            kmlLayer?.addLayerToMap()
        }*/
    }

}