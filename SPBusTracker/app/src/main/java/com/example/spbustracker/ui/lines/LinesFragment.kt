package com.example.spbustracker.ui.lines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.spbustracker.BuildConfig.SPTRANS_TOKEN
import com.example.spbustracker.databinding.FragmentLinesBinding
import com.example.spbustracker.network.SPTransApiService
import com.example.spbustracker.repository.AutenticacaoRepository
import com.example.spbustracker.repository.VehicleRepository
import com.example.spbustracker.ui.vehicles.SearchVehiclesManager
import com.example.spbustracker.viewmodel.AutenticacaoViewModel
import com.example.spbustracker.viewmodel.AutenticacaoViewModelFactory
import com.example.spbustracker.viewmodel.VehiclesViewModel
import com.example.spbustracker.viewmodel.VehiclesViewModelFactory

class LinesFragment : Fragment() {

    private lateinit var viewModel: VehiclesViewModel
    private lateinit var autenticacaoViewModel: AutenticacaoViewModel
    private var _binding: FragmentLinesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: LinesAdapter
    private lateinit var searchManager: SearchVehiclesManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        autenticarNaApi()
        configurarViewModelDeLinhas()
    }

    private fun autenticarNaApi() {
        val token = SPTRANS_TOKEN

        val autenticacaoRepository = AutenticacaoRepository(
            SPTransApiService.create(token, requireContext(), addInterceptor = false)
        )
        val autenticacaoFactory = AutenticacaoViewModelFactory(autenticacaoRepository)
        autenticacaoViewModel =
            ViewModelProvider(this, autenticacaoFactory)[AutenticacaoViewModel::class.java]

        autenticacaoViewModel.autenticar(token)

        autenticacaoViewModel.autenticado.observe(viewLifecycleOwner) { autenticado ->
            if (autenticado) {
                Toast.makeText(requireContext(), "Carregando Linhas...", Toast.LENGTH_SHORT).show()
                configurarViewModelDeLinhas()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Falha ao carregar as linhas...",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun configurarViewModelDeLinhas() {
        val token = SPTRANS_TOKEN
        val repository = VehicleRepository(
            SPTransApiService.create(
                token,
                context = requireContext(),
                addInterceptor = true
            )
        )
        val factory = VehiclesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[VehiclesViewModel::class.java]

        setupRecyclerView()
        setupSearchView()

        viewModel.filteredVehicles.observe(viewLifecycleOwner) { vehicles ->
            val lines = vehicles?.map { it.first } ?: emptyList()
        }

        viewModel.loadVehicles()
    }

    private fun setupRecyclerView() {
        adapter = LinesAdapter(requireContext(), emptyList()) { line ->
        }

        binding.recyclerViewLines.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewLines.adapter = adapter
    }


    private fun setupSearchView() {
        searchManager = SearchVehiclesManager(binding.searchViewLines) { query ->
            viewModel.searchVehicles(query)
        }
        searchManager.setupSearchView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
