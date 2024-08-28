package br.com.aiko.projetoolhovivo.ui.line.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aiko.projetoolhovivo.databinding.ListLineFragmentBinding
import br.com.aiko.projetoolhovivo.ui.line.LineViewModel
import br.com.aiko.projetoolhovivo.ui.line.adapter.ListLineAdapter
import br.com.aiko.projetoolhovivo.ui.main.MainViewModel
import dagger.android.support.DaggerFragment

class ListLineFragment: DaggerFragment() {
    lateinit var viewModel: LineViewModel

    lateinit var adapter: ListLineAdapter
    private lateinit var binding: ListLineFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListLineFragmentBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity())[LineViewModel::class.java]

        viewModel.isLoading.observe(viewLifecycleOwner, this::updateLoading)
        viewModel.lines.observe(viewLifecycleOwner, adapter::updateList)
        viewModel.error.observe(viewLifecycleOwner) { error ->
            run {
                Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.getLines()
    }

    private fun updateLoading(isLoading: Boolean){
        binding.pbLoadingListLine.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupRecyclerView() {
        adapter = ListLineAdapter()
        binding.rvListLine.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.rvListLine.adapter = adapter
    }
}