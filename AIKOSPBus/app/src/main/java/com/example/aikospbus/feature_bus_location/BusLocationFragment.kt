package com.example.aikospbus.feature_bus_location

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aikospbus.databinding.FragmentBusLocationBinding

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

        return binding.root
    }
}