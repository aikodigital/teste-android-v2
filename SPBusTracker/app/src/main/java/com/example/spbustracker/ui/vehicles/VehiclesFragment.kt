package com.example.spbustracker.ui.vehicles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spbustracker.R
import com.example.spbustracker.databinding.FragmentVehiclesBinding
import com.example.spbustracker.viewmodel.VehiclesViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class VehiclesFragment : Fragment() {

    private lateinit var viewModel: VehiclesViewModel
    private var _binding: FragmentVehiclesBinding? = null
    private val binding get() = _binding!!

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.clear()

        viewModel.vehicles.observe(viewLifecycleOwner) { vehicles ->
            googleMap.clear()
            vehicles.forEach { vehicle ->
                val vehiclePosition = LatLng(vehicle.latitude, vehicle.longitude)
                googleMap.addMarker(
                    MarkerOptions().position(vehiclePosition)
                        .title("Veículo da linha ${vehicle.lineId}")
                )
            }

            if (vehicles.isNotEmpty()) {
                val firstVehicle = vehicles.first()
                val firstPosition = LatLng(firstVehicle.latitude, firstVehicle.longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstPosition, 12f))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVehiclesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(VehiclesViewModel::class.java)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { lineNumber ->
                    viewModel.searchLine(lineNumber)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
