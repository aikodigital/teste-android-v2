package com.matreis.teste.sptrans.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matreis.teste.sptrans.databinding.DialogBusStopBinding
import com.matreis.teste.sptrans.databinding.DialogInfoBusTimeBinding
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.Vehicle
import com.matreis.teste.sptrans.helper.getDirection

class DialogBusInfo(
    private val vehicle: Vehicle
) : BottomSheetDialogFragment() {

    /*private lateinit var vehicle: Vehicle
    fun setVehicle(vehicle: Vehicle) {
        this.vehicle = vehicle
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogInfoBusTimeBinding.inflate(inflater, container, false)
        binding.apply {
            tvLineSign.text = vehicle.line?.fullSign
            tvLineDescription.text = vehicle.line?.getDirection()
            tvBusCode.text = vehicle.prefix
            tvBusTime.text = vehicle.estimatedArrivalTime
        }

        return binding.root
    }

}