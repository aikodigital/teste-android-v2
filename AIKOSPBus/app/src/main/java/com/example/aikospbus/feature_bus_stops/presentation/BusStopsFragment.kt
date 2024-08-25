package com.example.aikospbus.feature_bus_stops.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aikospbus.ApiConfig
import com.example.aikospbus.databinding.FragmentBusStopsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusStopsFragment : Fragment() {

    private var _binding: FragmentBusStopsBinding? = null
    private val binding get() = _binding!!

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

        viewModel.getRemoteBusStopsData(ApiConfig.cookie,"Afonso")
        viewModel.busDtoStopsDataModel.observe(viewLifecycleOwner) { busLocationData ->
            println("VIEWMODEL: ${busLocationData?.nomeParada}")
            println("VIEWMODEL: ${busLocationData?.latitude}")
            println("VIEWMODEL: ${busLocationData?.longitude}")
        }

        return binding.root
    }
}