package com.matreis.teste.sptrans.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.databinding.FragmentBusStopBinding
import com.matreis.teste.sptrans.presentation.adapter.BusStopWithVehicleAdapter
import com.matreis.teste.sptrans.viewmodels.BusStopViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusStopFragment : Fragment() {

    private lateinit var binding: FragmentBusStopBinding
    private val busStopViewModel: BusStopViewModel by viewModels()
    private val busStopAdapter: BusStopWithVehicleAdapter by lazy { BusStopWithVehicleAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusStopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
        setupRecyclerView()
        initObservers()
    }

    private fun setupRecyclerView() {
        binding.rvBusStop.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = busStopAdapter
            setHasFixedSize(true)
        }
    }

    private fun initObservers() {
        busStopViewModel.busStops.observe(viewLifecycleOwner) {
            busStopAdapter.submitList(it)
        }
    }

    private fun initListeners() {
        binding.apply {
            searchBar.setOnQueryTextListener(object: OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    p0?.let {
                        busStopViewModel.getBusStop(it)
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return true
                }

            })
        }
    }

}