package com.example.spbustracker.ui.vehicles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spbustracker.R
import com.example.spbustracker.databinding.FragmentVehiclesBinding
import com.example.spbustracker.network.SPTransApiService
import com.example.spbustracker.repository.VehicleRepository
import com.example.spbustracker.viewmodel.VehiclesViewModel
import com.example.spbustracker.viewmodel.VehiclesViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehiclesFragment : Fragment() {

    private lateinit var viewModel: VehiclesViewModel
    private var _binding: FragmentVehiclesBinding? = null
    private val binding get() = _binding!!

    private val callback = OnMapReadyCallback { googleMap ->
        viewModel.vehicles.observe(viewLifecycleOwner) { vehicles ->
            if (!vehicles.isNullOrEmpty()) {
                googleMap.clear()
                vehicles.forEach { vehicle ->
                    val position = LatLng(vehicle.py, vehicle.px)
                    googleMap.addMarker(
                        MarkerOptions().position(position).title("Veículo da linha ${vehicle.p}")
                    )
                }
                val firstVehicle = vehicles.first()
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            firstVehicle.py,
                            firstVehicle.px
                        ), 12f
                    )
                )
            } else {
                googleMap.clear()
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
            val token = getString(R.string.sptrans_token)
            val repository = VehicleRepository(SPTransApiService.create(token, context = requireContext(), addInterceptor = true))
            val factory = VehiclesViewModelFactory(repository)

            viewModel = ViewModelProvider(this, factory)[VehiclesViewModel::class.java]

            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            mapFragment?.getMapAsync(callback)

            loadVehiclesWithRetry()
        } catch (ex: Exception) {
            showErrorDialog(ex.message ?: "Erro desconhecido")
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        loadVehiclesWithRetry()
    }

    private fun loadVehiclesWithRetry() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                viewModel.loadVehicles()
            } catch (e: Exception) {
                showErrorDialog(e.message ?: "Erro desconhecido")
            }
        }
    }

    private fun showErrorDialog(message: String) {
        activity?.runOnUiThread {
            AlertDialog.Builder(requireContext())
                .setTitle("Erro")
                .setMessage(message)
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
