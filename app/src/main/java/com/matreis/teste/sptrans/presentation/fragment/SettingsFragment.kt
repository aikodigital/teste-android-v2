package com.matreis.teste.sptrans.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.databinding.FragmentSettingsBinding
import com.matreis.teste.sptrans.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        settingsViewModel.getConfigs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        settingsViewModel.isAutoUpdateEnabled.observe(viewLifecycleOwner) {
            binding.switchAutoUpdate.isChecked = it
            binding.llUpdateFrequency.visibility = if (it) View.VISIBLE else View.GONE
        }
        settingsViewModel.autoUpdateInterval.observe(viewLifecycleOwner) {
            when (it) {
                5 -> binding.radioGroup.check(R.id.rbFiveSeconds)
                10 -> binding.radioGroup.check(R.id.rbTenSeconds)
                15 -> binding.radioGroup.check(R.id.rbFifteenSeconds)
                20 -> binding.radioGroup.check(R.id.rbTwentySeconds)
            }
        }
    }



    private fun initListeners() {
        binding.switchAutoUpdate.setOnCheckedChangeListener { _, isChecked ->
            settingsViewModel.setAutoUpdateEnabled(isChecked)
            binding.llUpdateFrequency.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        binding.radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbFiveSeconds -> settingsViewModel.setAutoUpdateInterval(5)
                R.id.rbTenSeconds -> settingsViewModel.setAutoUpdateInterval(10)
                R.id.rbFifteenSeconds -> settingsViewModel.setAutoUpdateInterval(15)
                R.id.rbTwentySeconds -> settingsViewModel.setAutoUpdateInterval(30)
            }
        }
    }
}