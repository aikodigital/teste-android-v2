package com.cesarsoftdevelopment.aikopublictransport.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.cesarsoftdevelopment.aikopublictransport.R
import com.cesarsoftdevelopment.aikopublictransport.databinding.FragmentSearchHistoricBinding

class SearchHistoricFragment : Fragment() {

    private lateinit var binding : FragmentSearchHistoricBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = setUpView(inflater, container)

    private fun setUpView(inflater: LayoutInflater, container: ViewGroup?) : View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_historic,
            container,
            false
        )
        return binding.root
    }
}