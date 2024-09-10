package com.example.spbustracker.ui.vehicles

import CustomInfoWindowAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.widget.SearchView
import com.example.spbustracker.BuildConfig.SPTRANS_TOKEN
import com.example.spbustracker.R
import com.example.spbustracker.databinding.FragmentVehiclesBinding
import com.example.spbustracker.network.SPTransApiService
import com.example.spbustracker.repository.VehicleRepository
import com.example.spbustracker.viewmodel.VehiclesViewModel
import com.example.spbustracker.viewmodel.VehiclesViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehiclesFragment : Fragment() {

    private lateinit var viewModel: VehiclesViewModel
    private var _binding: FragmentVehiclesBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("PotentialBehaviorOverride")
    private val callback = OnMapReadyCallback { googleMap ->

        val saoPauloLatLng = LatLng(-23.55052, -46.633308)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(saoPauloLatLng, 15f))
        binding.progressBar.visibility = View.VISIBLE
        googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(requireContext()))

        viewModel.vehicles.observe(viewLifecycleOwner) { vehicles ->
            if (!vehicles.isNullOrEmpty()) {
                googleMap.clear()
                vehicles.forEach { (line, vehicle) ->
                    val position = LatLng(vehicle.py, vehicle.px)
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(position)
                            .title("Linha: ${line.c}")
                            .snippet("Origem: ${line.lt1}\nDestino: ${line.lt0}")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_marker))
                    )
                }
                val firstVehicle = vehicles.first().second
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            firstVehicle.py,
                            firstVehicle.px
                        ), 15f
                    )
                )
                binding.progressBar.visibility = View.GONE
            } else {
                googleMap.clear()
                binding.progressBar.visibility = View.GONE
                Log.d("VehiclesFragment", "Nenhum veículo encontrado")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehiclesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val token = SPTRANS_TOKEN
            val repository = VehicleRepository(
                SPTransApiService.create(
                    token,
                    context = requireContext(),
                    addInterceptor = true
                )
            )
            val factory = VehiclesViewModelFactory(repository)

            viewModel = ViewModelProvider(this, factory)[VehiclesViewModel::class.java]

            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(callback)

            setupSearchView()
            loadVehiclesWithRetry()
        } catch (ex: Exception) {
            showErrorDialog(ex.message ?: "Erro desconhecido")
        }
    }

    private fun setupSearchView() {
        val searchView = binding.searchView
        searchView.queryHint = "Ex.: 8200-10"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchVehicles(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun searchVehicles(lineNumber: String) {
        val filteredVehicles = viewModel.vehicles.value?.filter { (line, _) ->
            line.c.contains(lineNumber, ignoreCase = true)
        }

        if (!filteredVehicles.isNullOrEmpty()) {
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync { googleMap ->
                googleMap.clear()
                filteredVehicles.forEach { (line, vehicle) ->
                    val position = LatLng(vehicle.py, vehicle.px)
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(position)
                            .title("Linha: ${line.c}")
                            .snippet("Origem: ${line.lt1}\nDestino: ${line.lt0}")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_marker))
                    )
                }

                val firstVehicle = filteredVehicles.first().second
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(firstVehicle.py, firstVehicle.px),
                        15f
                    )
                )
            }
        } else {
            showErrorDialog("Nenhum veículo encontrado para a linha $lineNumber")
        }
    }

    private fun loadVehiclesWithRetry() {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                viewModel.loadVehicles()
            } catch (e: Exception) {
                showErrorDialog(e.message ?: "Erro desconhecido")
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun showErrorDialog(message: String) {
        activity?.runOnUiThread {
            AlertDialog.Builder(requireContext())
                .setTitle("Ops")
                .setMessage(message)
                .setIcon(android.R.drawable.ic_menu_info_details)
                .setPositiveButton("Retry") { _, _ ->
                    loadVehiclesWithRetry()
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
