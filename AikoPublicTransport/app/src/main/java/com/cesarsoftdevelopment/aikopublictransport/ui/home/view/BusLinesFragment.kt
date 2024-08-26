package com.cesarsoftdevelopment.aikopublictransport.ui.home.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.cesarsoftdevelopment.aikopublictransport.R
import com.cesarsoftdevelopment.aikopublictransport.databinding.FragmentBusLinesBinding
import com.cesarsoftdevelopment.aikopublictransport.ui.home.adapters.BusLinesAdapter
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.BusLinesViewModel
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
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
        busLinesAdapter = (activity as HomeActivity).busLinesAdapter
        busLinesViewModel = (activity as HomeActivity).busLinesViewModel
        setUpBinding()
        setList()
        setSearchView()
        observeSelectedLineCode()
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
            Log.i("LOG_FRAGMENT", "Selected line code: $selectedLineCode")
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
                }
            }
        })

    }

    private fun setList() {
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


}