package com.cesarsoftdevelopment.aikopublictransport.ui.home.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.cesarsoftdevelopment.aikopublictransport.R
import com.cesarsoftdevelopment.aikopublictransport.data.model.Objects
import com.cesarsoftdevelopment.aikopublictransport.data.model.StopItem
import com.cesarsoftdevelopment.aikopublictransport.databinding.FragmentBusLinesBinding
import com.cesarsoftdevelopment.aikopublictransport.ui.home.adapters.BusLinesAdapter
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.BusLinesViewModel
import com.cesarsoftdevelopment.aikopublictransport.utils.AppStrings
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class BusLinesFragment : Fragment() {
    private lateinit var binding : FragmentBusLinesBinding
    private lateinit var busLinesAdapter: BusLinesAdapter
    private lateinit var busLinesViewModel : BusLinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bus_lines,
            container,
            false
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        busLinesViewModel = (activity as HomeActivity).busLinesViewModel
        setUpBinding()
        setList()
        busLinesViewModel.removeObserve()
        observeSelectedLineCode()
        setSearchView()
    }

    private fun setSearchView() {
        binding.searchView
            .editText
            .setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
                binding.searchBar.setText(binding.searchView.text)
                val termsSearch = binding.searchView.text.toString()
                getBusLines(termsSearch)
                binding.searchView.hide()
                false
            }

    }

    private fun observeSelectedLineCode() {
        busLinesViewModel.selectedLineCode.observe(viewLifecycleOwner, Observer { selectedLineCode ->
            if(selectedLineCode != null) {
                binding.fabGoMap.apply {
                    visibility = View.VISIBLE
                    setOnClickListener {
                        getStops(selectedLineCode)
                    }
                }

            }else {
                binding.fabGoMap.visibility = View.GONE
            }
        })
    }


    private fun getStops(lineCode : Int) {
        busLinesViewModel.getStopsByLine(lineCode)
        busLinesViewModel.stops.observe(viewLifecycleOwner, Observer { stopsResponse ->
            when (stopsResponse) {
                is Resource.Success -> {
                    if(!stopsResponse.data.isNullOrEmpty()) {
                        Log.i("Bus log", "Success: ${stopsResponse.data}")
                        getVehicle(
                            lineCode,
                            stopsResponse.data
                        )
                    }else {
                        Toast.makeText(
                            requireContext(),
                            "Sem paradas",
                            Toast.LENGTH_SHORT
                        ).show()
                        busLinesViewModel.removeObserve()
                        hideProgressBar()
                    }

                }

                is Resource.Error -> {
                    Log.e(AppStrings.ERROR, "${stopsResponse.message}")
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                else -> {}
            }
        })

    }

    private fun getVehicle(lineCode : Int, stopItems: List<StopItem>?) {
        busLinesViewModel.getVehicles(lineCode)
        busLinesViewModel.vehicles.observe(viewLifecycleOwner, Observer { vehicleResponse ->
            when (vehicleResponse) {
                is Resource.Success -> {
                    if (!stopItems.isNullOrEmpty()) {

                        if(vehicleResponse.data != null && !vehicleResponse.data.vehicles.isNullOrEmpty()) {
                            val objects = Objects(
                                stopItems,
                                vehicleResponse.data
                            )

                            navigateToMapFragment(objects)

                        }else {
                            Toast.makeText(
                                requireContext(),
                                "Sem Veículos",
                                Toast.LENGTH_SHORT
                            ).show()
                            busLinesViewModel.removeObserve()
                            hideProgressBar()
                        }
                    }else {
                        Toast.makeText(
                            requireContext(),
                            "Sem Paradas",
                            Toast.LENGTH_SHORT
                        ).show()
                        busLinesViewModel.removeObserve()
                        hideProgressBar()
                    }

                }

                is Resource.Error -> {
                    Log.e("BindingAdapter", "Error: ${vehicleResponse.message}")
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                else -> {
                    Log.e("BindingAdapter", "Error: $vehicleResponse")
                }
            }
        })

    }

    private fun getBusLines(termsSearch : String) {
        busLinesViewModel.getBusLines(termsSearch)
        busLinesViewModel.busLines.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    busLinesAdapter.submitList(response.data)
                }

                is Resource.Error -> {
                    Log.e("BindingAdapter", "Error: ${response.message}")
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                else -> {
                    Log.e("BindingAdapter", "Error: $response")
                    hideProgressBar()
                }
            }
        })

    }

    private fun setList() {
        busLinesAdapter = BusLinesAdapter(busLinesViewModel)
        binding.recyclerView.apply {
            adapter = busLinesAdapter
        }
    }
    private fun setUpBinding() {
        binding.lifecycleOwner = this
    }


    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun navigateToMapFragment(objects: Objects?) {
        try {
            val navController = requireView().findNavController()
            val currentDestination = navController.currentDestination?.id

            if (currentDestination == R.id.busLinesFragment) {
                navController.navigate(
                    BusLinesFragmentDirections.actionBusLinesFragmentToMapsFragment(objects)
                )
            } else {
                Log.e(AppStrings.NAVIGATION_ERROR, currentDestination.toString())
            }
        } catch (e: Exception) {
            Log.e(AppStrings.NAVIGATION_EXCEPTION, e.toString())
        }
    }

}