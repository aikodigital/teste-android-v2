package com.matreis.teste.sptrans.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.databinding.FragmentBusStopTimesBinding
import com.matreis.teste.sptrans.presentation.adapter.LineWithVehicleAdapter
import com.matreis.teste.sptrans.viewmodels.ArrivalTimesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusStopTimesFragment : Fragment() {

    private lateinit var binding: FragmentBusStopTimesBinding
    private val arrivalTimesViewModel: ArrivalTimesViewModel by viewModels()
    private val lineWithVehicleAdapter by lazy { LineWithVehicleAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null) {
            val stopCode = arguments?.getLong("stopCod")
            stopCode?.let {
                arrivalTimesViewModel.getArrivalTimes(it)
            }
            val lineCode = arguments?.getLong("lineCod")
            lineCode?.let {
                arrivalTimesViewModel.setSelectedLine(lineCode)
                lineWithVehicleAdapter.setLineSelected(lineCode)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusStopTimesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        iniListeners()
        setupRecyclerView()
        iniObservers()
    }

    private fun setupRecyclerView() {
        binding.rvLinesWithBus.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = lineWithVehicleAdapter
            setHasFixedSize(true)
        }
    }

    private fun iniObservers() {
        arrivalTimesViewModel.timeWithBusStop.observe(viewLifecycleOwner) {
            it.busStop?.let { busStop ->
                binding.tvBusStop.text = busStop.name
                binding.tvBusAddress.text = busStop.stopCod.toString()
                lineWithVehicleAdapter.submitList(it.busStop!!.lines)
            }
            handleEmptyList(it.busStop == null || it.busStop!!.lines.isEmpty())
        }
        arrivalTimesViewModel.isLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.clEmpty.visibility = View.GONE
                binding.rvLinesWithBus.visibility = View.GONE
            }
            binding.progressLines.visibility = if (it) View.VISIBLE else View.GONE
        }
        arrivalTimesViewModel.error.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { messageId ->
                handleEmptyList(true)
                Snackbar.make(binding.root, getString(messageId), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun iniListeners() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun handleEmptyList(empty: Boolean) {
        binding.progressLines.visibility = View.GONE
        if (empty) {
            binding.rvLinesWithBus.visibility = View.GONE
            binding.clEmpty.visibility = View.VISIBLE
        }else {
            binding.rvLinesWithBus.visibility = View.VISIBLE
            binding.clEmpty.visibility = View.GONE
        }
    }


}