package com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetEstimatedArrivalTimesByStopUseCase

class MapViewModelFactory(
    private val application: Application,
    private val getEstimatedArrivalTimesByStopUseCase : GetEstimatedArrivalTimesByStopUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MapViewModel(
            application,
            getEstimatedArrivalTimesByStopUseCase
        ) as T
    }
}