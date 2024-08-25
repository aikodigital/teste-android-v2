package com.example.aikospbus.feature_bus_corridor.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aikospbus.ApiConfig
import com.example.aikospbus.databinding.FragmentBusCorridorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusCorridorFragment : Fragment() {

    private var _binding: FragmentBusCorridorBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = BusCorridorFragment()
    }

    private val viewModel: BusCorridorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusCorridorBinding.inflate(inflater, container, false)

        viewModel.getRemoteBusCorridorData(ApiConfig.cookie)
        viewModel.busDtoCorridorDataModel.observe(viewLifecycleOwner) { busLocationData ->
            println("VIEWMODEL: ${busLocationData?.codigoCorredor}")
            println("VIEWMODEL: ${busLocationData?.nomeCorredor}")
        }

        return binding.root
    }
}