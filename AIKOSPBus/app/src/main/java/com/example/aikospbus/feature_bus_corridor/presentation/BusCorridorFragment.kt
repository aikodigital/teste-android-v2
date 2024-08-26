package com.example.aikospbus.feature_bus_corridor.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aikospbus.common.custom_components.CustomHeader
import com.example.aikospbus.databinding.FragmentBusCorridorBinding
import com.example.aikospbus.feature_api_sp_trans.remote.api.CookieManager
import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BusCorridorFragment : Fragment() {

    private var _binding: FragmentBusCorridorBinding? = null
    private val binding get() = _binding!!

    private var busCorridorList: ArrayList<BusCorridorModel> = ArrayList()
    private val busCorridorAdapter = BusCorridorAdapter(busCorridorList)

    companion object {
        fun newInstance() = BusCorridorFragment()
    }

    private val viewModel: BusCorridorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusCorridorBinding.inflate(inflater, container, false)

        setHeaderConfig()
        handleApiCookies()

        viewModel.busCorridorLiveData.observe(viewLifecycleOwner) { newList ->
            busCorridorList.clear()
            if (newList != null) {
                busCorridorList.addAll(newList)
                busCorridorAdapter.notifyItemChanged(
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
        busCorridorAdapter.setOnItemClickListener(object : BusCorridorAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
//                findNavController().navigate(R.id.action_FirstFragment_to_busCorridorFragment)
            }
        })
    }

    private fun setRecyclerView() {
        binding.apply {
            busCorridorRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                itemAnimator = null
                adapter = busCorridorAdapter
                setHasFixedSize(false)
            }
        }
    }

    private fun setHeaderConfig() {
        val customHeader = binding.customHeader

        customHeader.setConfig(object : CustomHeader.HeaderClickListener {
            override fun setBackButtonClickListener() {
                findNavController().popBackStack()
            }
        }, title = "Lista corredores")
    }

    private fun handleApiCookies() {
        CoroutineScope(Dispatchers.Main).launch {
            if (CookieManager.isCookieValid()) {
                viewModel.getRemoteBusCorridorData(CookieManager.cookie)
            } else {
                CookieManager.authentication()
                viewModel.getRemoteBusCorridorData(CookieManager.cookie)
            }
        }
    }
}