package com.example.myapplication.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapter.LineAdapter
import com.example.myapplication.adapter.ListItem
import com.example.myapplication.databinding.FragmentLineBinding
import com.example.myapplication.viewModel.LineViewModel
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class LineFragment : Fragment() {

    private lateinit var binding: FragmentLineBinding
    private lateinit var lineAdapter: LineAdapter
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private val viewModel: LineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                initializeMap()
            } else {
                showPermissionRationale()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLineBinding.inflate(inflater, container, false)

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            initializeMap()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        Glide.with(requireContext())
            .asGif()
            .load(R.drawable.onibus)
            .into(binding.imageView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapters()
        configRv()
        searchLine()
        observeViewModel()
    }

    private fun initializeAdapters() {
        lineAdapter = LineAdapter(requireContext(), mutableListOf()) { listItem ->
            val lineCode = (listItem as? ListItem.BusLine)?.vehicle?.line?.lineCode
                ?: (listItem as? ListItem.Line)?.line?.lineCode

            lineCode?.toIntOrNull()?.let { code ->
                navigateToMapFragment(code)
            } ?: Toast.makeText(requireContext(), "Código da linha inválido", Toast.LENGTH_SHORT).show()
        }
    }

    private fun configRv() {
        binding.rv.apply {
            adapter = lineAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun searchLine() {
        binding.searchBtn.setOnClickListener {
            val termoBusca = binding.searchInput.text.toString()
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.fetchBusLine(termoBusca)
            }
            binding.informationContainer.visibility = View.GONE
            binding.rv.visibility = View.VISIBLE
        }
    }

    private fun observeViewModel() {
        viewModel.lineList.observe(viewLifecycleOwner, Observer { lines ->
            val linesMutable = lines.map { ListItem.Line(it) }
            lineAdapter.setData(linesMutable)
        })
    }

    private fun navigateToMapFragment(lineCode: Int) {
        val mapFragment = MapFragment.newInstance()
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, mapFragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()

        parentFragmentManager.executePendingTransactions()

        lifecycleScope.launch {
            delay(100) // Ajuste o delay conforme necessário
            val fragment = parentFragmentManager.findFragmentById(R.id.mainContainer) as? MapFragment
            fragment?.updateMapWithLineCode(lineCode)
        }
    }

    private fun showPermissionRationale() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permissão de Localização Necessária")
            .setMessage("Para melhor uso do app, disponibilize sua localização.")
            .setPositiveButton("OK") { _, _ ->
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun initializeMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->
            // Configurações do mapa aqui
        }
    }
}