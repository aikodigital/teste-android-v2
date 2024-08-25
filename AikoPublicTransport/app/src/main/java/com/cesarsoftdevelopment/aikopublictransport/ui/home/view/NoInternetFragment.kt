package com.cesarsoftdevelopment.aikopublictransport.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cesarsoftdevelopment.aikopublictransport.R
import com.cesarsoftdevelopment.aikopublictransport.databinding.FragmentNoInternetBinding
import kotlin.system.exitProcess

class NoInternetFragment : Fragment() {

    private lateinit var binding: FragmentNoInternetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoInternetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.leftApp.setOnClickListener {
            requireActivity().finish()
        }
    }

}