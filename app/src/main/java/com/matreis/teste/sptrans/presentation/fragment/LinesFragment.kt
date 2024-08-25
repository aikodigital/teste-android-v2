package com.matreis.teste.sptrans.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.databinding.FragmentLinesBinding
import com.matreis.teste.sptrans.presentation.adapter.LineAdapter
import com.matreis.teste.sptrans.viewmodels.LinesViewModel
import com.matreis.teste.sptrans.viewmodels.MapViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LinesFragment : Fragment() {

    private lateinit var binding: FragmentLinesBinding
    private val linesViewModel: LinesViewModel by viewModels()
    //private val mapViewModel: MapViewModel by activityViewModels()
    private val lineAdapter by lazy { LineAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
        setupRecyclerView()
        initAdapterListeners()
        initObservers()
    }

    private fun initAdapterListeners() {
        lineAdapter.onSeeOnMapClick = { line ->
            val bundle = Bundle()
            bundle.putSerializable("line", line)
            findNavController().navigate(R.id.action_linesFragment_to_mapFragment, bundle)
        }
        lineAdapter.onSeeBusStopsClick = { line ->
            //binding.progressClick.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        binding.rvLines.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = lineAdapter
        }
    }

    private fun initObservers() {
        linesViewModel.lines.observe(viewLifecycleOwner) {
            handleEmptyList(it.isEmpty())
            lineAdapter.submitList(it)
        }
        linesViewModel.error.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { messageId ->
                handleEmptyList(true)
                Snackbar.make(binding.root, getString(messageId), Snackbar.LENGTH_SHORT).show()
            }
        }
        linesViewModel.isLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.clHint.visibility = View.GONE
                binding.clEmpty.visibility = View.GONE
                binding.rvLines.visibility = View.GONE
            }
            binding.progressLines.visibility = if (it) View.VISIBLE else View.GONE
        }


    }

    private fun handleEmptyList(empty: Boolean) {
        binding.progressLines.visibility = View.GONE
        if (empty) {
            binding.rvLines.visibility = View.GONE
            binding.clHint.visibility = View.GONE
            binding.clEmpty.visibility = View.VISIBLE
        }else {
            binding.rvLines.visibility = View.VISIBLE
            binding.clEmpty.visibility = View.GONE
            binding.clHint.visibility = View.GONE
        }
    }

    private fun initListeners() {
        binding.apply {
            searchBar.setOnQueryTextListener(object: OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    p0?.let {
                        linesViewModel.getLinesByTerm(it)
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