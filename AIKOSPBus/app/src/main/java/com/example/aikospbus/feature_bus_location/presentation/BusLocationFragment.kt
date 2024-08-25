package com.example.aikospbus.feature_bus_location.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aikospbus.ApiConfig
import com.example.aikospbus.databinding.FragmentBusLocationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusLocationFragment : Fragment() {

    private var _binding: FragmentBusLocationBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = BusLocationFragment()
    }

    private val viewModel: BusLocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusLocationBinding.inflate(inflater, container, false)

//        val busLocationObject = BusLocation(
//            id = 0,
//            word = "Teste",
//        )
//
//        viewModel.insertBusLocation(busLocationObject)

        viewModel.getRemoteBusLocationData(ApiConfig.cookie,2506)
        viewModel.busDtoLocationDataModel.observe(viewLifecycleOwner) { busLocationData ->
//            println("VIEWMODEL: ${busLocationData?.veiculos[0].latitude}")
            println("VIEWMODEL: ${busLocationData?.veiculos}")
        }
        return binding.root
    }
}