package com.aiko.teste.sptrans.screens.bus_stop_tracking

import androidx.lifecycle.ViewModel
import com.aiko.teste.sptrans.data.objects.BusStop
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusStopTrackingViewModel @Inject constructor(private val busStopsRepository: BusStopsRepository) :
    ViewModel() {

        fun getBusStop(busStopCode: String): BusStop = busStopsRepository.getBusStop(busStopCode)
}