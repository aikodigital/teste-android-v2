package com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetBusLinesUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetStopsByLineUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetVehiclesPositionByLineUseCase
class BusLinesViewModelFactory(
    private val getBusLinesUseCase: GetBusLinesUseCase,
    private val getVehiclesPositionByLineUseCase: GetVehiclesPositionByLineUseCase,
    private val getStopsByLineUseCase: GetStopsByLineUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BusLinesViewModel(
            getBusLinesUseCase,
            getVehiclesPositionByLineUseCase,
            getStopsByLineUseCase
        ) as T
    }
}