package com.example.aikospbus.feature_bus_lines.presentation

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
import com.example.aikospbus.databinding.FragmentBusLinesBinding
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusLinesFragment : Fragment() {

    private var _binding: FragmentBusLinesBinding? = null
    private val binding get() = _binding!!

    private var busLinesList : ArrayList<BusLinesModel> = ArrayList()
    private val busLinesAdapter = BusLinesAdapter(busLinesList)

    companion object {
        fun newInstance() = BusLinesFragment()
    }

    private val viewModel: BusLinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusLinesBinding.inflate(inflater, container, false)

        viewModel.getRemoteBusLinesData(ApiConfig.cookie,ApiConfig.searchBusLines)


        viewModel.busLinesLiveData.observe(viewLifecycleOwner) { newList ->
            busLinesList.clear()
            if (newList != null) {
                busLinesList.addAll(newList)
                busLinesAdapter.notifyItemChanged(
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
        busLinesAdapter.setOnItemClickListener(object : BusLinesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
//                findNavController().navigate(R.id.action_FirstFragment_to_busCorridorFragment)
            }
        })
    }

    private fun setRecyclerView() {
        binding.apply {
            busLinesRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                itemAnimator = null
                adapter = busLinesAdapter
                setHasFixedSize(false)
            }
        }
    }
}