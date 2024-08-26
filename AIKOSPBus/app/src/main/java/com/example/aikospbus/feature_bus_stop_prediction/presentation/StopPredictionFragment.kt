package com.example.aikospbus.feature_bus_stop_prediction.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aikospbus.ApiConfig
import com.example.aikospbus.R
import com.example.aikospbus.common.custom_components.CustomHeader
import com.example.aikospbus.databinding.FragmentBusLocationBinding
import com.example.aikospbus.databinding.FragmentStopPredictionBinding
import com.example.aikospbus.feature_api_sp_trans.remote.api.CookieManager
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import com.example.aikospbus.feature_bus_lines.presentation.BusLinesAdapter
import com.example.aikospbus.feature_bus_lines.presentation.BusLinesViewModel
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.VeiculosTestDto
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.VeiculoTestModel
import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel
import com.example.aikospbus.feature_bus_stops.presentation.BusStopsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StopPredictionFragment : Fragment() {

    private var _binding: FragmentStopPredictionBinding? = null
    private val binding get() = _binding!!

    private var busStopsList: ArrayList<VeiculosTestDto> = ArrayList()
    private val busStopsAdapter = StopPredictionAdapter(busStopsList)

    companion object {
        fun newInstance() = StopPredictionFragment()
    }

    private val viewModel: StopPredictionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStopPredictionBinding.inflate(inflater, container, false)

        setHeaderConfig()
        handleApiCookies()

        viewModel.veiculoListLiveData.observe(viewLifecycleOwner) { newList ->
            busStopsList.clear()
            if (newList != null) {
                busStopsList.addAll(newList)
                busStopsAdapter.notifyItemChanged(
                    0, newList.size
                )
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterOnClickListener()
        setRecyclerView()

    }

    private fun setAdapterOnClickListener() {
        busStopsAdapter.setOnItemClickListener(object : StopPredictionAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
            }
        })
    }

    private fun setRecyclerView() {

        binding.apply {
            busStopsPredictionRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                itemAnimator = null
                adapter = busStopsAdapter
                setHasFixedSize(false)
            }

            viewModel.busStopPredictionLiveData.observe(viewLifecycleOwner) {
                viewModel.updateVeiculoList()
            }
        }
    }

    private fun setHeaderConfig() {
        val customHeader = binding.customHeader

        customHeader.setConfig(object : CustomHeader.HeaderClickListener {
            override fun setBackButtonClickListener() {
                findNavController().popBackStack()
            }
        }, title = "Lista veiculos")
    }

    private fun handleApiCookies() {
        CoroutineScope(Dispatchers.Main).launch {
            if (CookieManager.isCookieValid()) {
                viewModel.getRemoteBusStopsPredictionData(CookieManager.cookie, ApiConfig.stopCode)
            } else {
                CookieManager.authentication()
                viewModel.getRemoteBusStopsPredictionData(CookieManager.cookie, ApiConfig.stopCode)
            }
        }
    }
}