package com.aiko.teste.sptrans.screens.bus_stop_tracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiko.teste.sptrans.data.objects.BusStop
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import com.aiko.teste.sptrans.data.repositories.LocationRepository
import com.mapbox.geojson.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusStopTrackingViewModel @Inject constructor(
    private val busStopsRepository: BusStopsRepository,
    private val locationSource: LocationRepository
) :
    ViewModel() {
    private val _centerLocation = MutableLiveData<Point>()
    val centerLocation: LiveData<Point> = _centerLocation

    fun getBusStop(busStopCode: String): BusStop = busStopsRepository.getBusStop(busStopCode)

    private fun updateCenterLocation(point: Point) {
        _centerLocation.value = point
    }

    fun getCenterLocation() = locationSource.getCurrentLocation(this::updateCenterLocation)
}