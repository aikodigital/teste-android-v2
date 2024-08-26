package com.matreis.teste.sptrans.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matreis.teste.sptrans.databinding.DialogBusStopBinding
import com.matreis.teste.sptrans.domain.model.BusStop

class DialogInfoBusStop(
    private val seeTimes: (BusStop) -> Unit,
    private val defineRoute: (BusStop) -> Unit
): BottomSheetDialogFragment() {

    private lateinit var busStop: BusStop
    fun setBusStop(busStop: BusStop) {
        this.busStop = busStop
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogBusStopBinding.inflate(inflater, container, false)
        binding.btnSeeTimes.setOnClickListener {
            seeTimes(busStop)
        }
        binding.btnDefineRoute.setOnClickListener {
            defineRoute(busStop)
        }
        binding.tvStopName.text = busStop.name
        binding.tvStopAddress.text = busStop.address

        return binding.root
    }

}