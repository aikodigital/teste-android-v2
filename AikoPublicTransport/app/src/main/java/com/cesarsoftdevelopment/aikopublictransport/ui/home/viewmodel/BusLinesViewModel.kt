package com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetBusLinesUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetStopsByLineUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetVehiclesPositionByLineUseCase

class BusLinesViewModel(
    private val getBusLinesUseCase: GetBusLinesUseCase,
    private val getVehiclesPositionByLineUseCase: GetVehiclesPositionByLineUseCase,
    private val getStopsByLineUseCase: GetStopsByLineUseCase
) : ViewModel() {





}