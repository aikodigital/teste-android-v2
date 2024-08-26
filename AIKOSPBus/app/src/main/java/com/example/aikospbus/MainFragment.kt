package com.example.aikospbus

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.aikospbus.R.drawable.arrow_down_ic
import com.example.aikospbus.R.drawable.arrow_up_ic
import com.example.aikospbus.databinding.FragmentMainBinding
import com.example.aikospbus.feature_api_sp_trans.ApiConfig
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        setButtonsClickListeners()
        handleLocationSearch()
        handleLinesSearch()
        handleStopsSearch()

        binding.mainLayout.setOnClickListener {
            hideSoftKeyboard()
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = requireActivity().currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


    private fun handleLocationSearch() {
        binding.busLocationBt.setOnClickListener {
            if (binding.busLocationSearch.visibility == View.GONE) {
                binding.busLocationSearch.visibility = View.VISIBLE
                binding.locationSearchArrow.setImageResource(arrow_up_ic)
            } else {
                binding.busLocationSearch.visibility = View.GONE
                binding.locationSearchArrow.setImageResource(arrow_down_ic)
            }
        }

        val searchView: SearchView = binding.busLocationSearch
        searchView.isIconifiedByDefault = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                ApiConfig.searchLocationLine = query?.toInt() ?: 11111
                findNavController().navigate(R.id.action_FirstFragment_to_busLocationFragment)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun handleLinesSearch() {
        binding.busLinesBt.setOnClickListener {
            if (binding.busLinesSearch.visibility == View.GONE) {
                binding.busLinesSearch.visibility = View.VISIBLE
                binding.linesSearchArrow.setImageResource(arrow_up_ic)
            } else {
                binding.busLinesSearch.visibility = View.GONE
                binding.linesSearchArrow.setImageResource(arrow_down_ic)
            }
        }

        val searchView: SearchView = binding.busLinesSearch
        searchView.isIconifiedByDefault = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                ApiConfig.searchBusLines = query.toString()
                findNavController().navigate(R.id.action_FirstFragment_to_busLinesFragment)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun handleStopsSearch() {
        binding.busStopsBt.setOnClickListener {
            if (binding.busStopsSearch.visibility == View.GONE) {
                binding.busStopsSearch.visibility = View.VISIBLE
                binding.stopsSearchArrow.setImageResource(arrow_up_ic)
            } else {
                binding.busStopsSearch.visibility = View.GONE
                binding.stopsSearchArrow.setImageResource(arrow_down_ic)
            }
        }

        val searchView: SearchView = binding.busStopsSearch
        searchView.isIconifiedByDefault = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                ApiConfig.searchStops = query.toString()
                findNavController().navigate(R.id.action_FirstFragment_to_busStopsFragment)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }


    private fun setButtonsClickListeners() {
        with(binding) {
            busCorridorBt.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_busCorridorFragment)
            }
        }
    }
}