package com.matreis.teste.sptrans.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.databinding.FragmentFavoriteLiensBinding
import com.matreis.teste.sptrans.presentation.adapter.LineAdapter
import com.matreis.teste.sptrans.viewmodels.FavoriteLinesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteLiensFragment : Fragment() {

    private val favoriteLinesViewModel: FavoriteLinesViewModel by viewModels()
    private lateinit var binding: FragmentFavoriteLiensBinding
    private val lineAdapter by lazy { LineAdapter(true) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteLiensBinding.inflate(inflater, container, false)
        //favoriteLinesViewModel.getFavoritesLines()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        favoriteLinesViewModel.getFavoritesLines().observe(viewLifecycleOwner) {
            handleEmptyList(it.isEmpty())
            lineAdapter.submitList(it)
        }
    }

    private fun handleEmptyList(empty: Boolean) {
        binding.progressLines.visibility = View.GONE
        if (empty) {
            binding.rvLines.visibility = View.GONE
            binding.clEmpty.visibility = View.VISIBLE
        }else {
            binding.rvLines.visibility = View.VISIBLE
            binding.clEmpty.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() {
        binding.rvLines.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = lineAdapter.also {
                it.onDeleteFromFavoriteClick = { line ->
                    favoriteLinesViewModel.deleteFavoriteLine(line.codLine!!)
                }
                it.onSeeOnMapClick = { line ->
                    val bundle = Bundle()
                    bundle.putSerializable("line", line)
                    findNavController().navigate(R.id.action_favoriteLiensFragment_to_mapFragment, bundle)
                }
            }
            setHasFixedSize(true)
        }
    }

}