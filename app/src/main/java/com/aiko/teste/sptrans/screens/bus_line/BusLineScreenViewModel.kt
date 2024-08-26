package com.aiko.teste.sptrans.screens.bus_line

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiko.teste.sptrans.data.objects.BusPosition
import com.aiko.teste.sptrans.data.repositories.BusLinesRepository
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import com.aiko.teste.sptrans.data.repositories.LocationRepository
import com.mapbox.geojson.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BusLineScreenViewModel @Inject constructor(
    private val busLinesRepository: BusLinesRepository,
    private val busStopsRepository: BusStopsRepository,
    private val locationSource: LocationRepository
) :
    ViewModel() {
    private val _busStops = MutableLiveData<List<Point>>()
    val busStops: LiveData<List<Point>> = _busStops

    private val _busPositions = MutableLiveData<List<BusPosition>>()
    val busPositions: LiveData<List<BusPosition>> = _busPositions

    private val _centerLocation = MutableLiveData<Point>()
    val centerLocation: LiveData<Point> = _centerLocation

    fun getBusStops(busLineCode: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                busStopsRepository.getBusStopsByLine(busLineCode)
            }
            if (result.isSuccess) {
                val busStops = result.getOrDefault(listOf())
                _busStops.value = busStops.map { Point.fromLngLat(it.longitude, it.latitude) }
            }
        }
    }

    fun getBusPositions(busLineCode: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                busLinesRepository.getBusPositions(busLineCode)
            }
            if (result.isSuccess) {
                val data = result.getOrNull()
                _busPositions.value = data?.positions ?: emptyList()
            }
        }
    }

    fun getCenterLocation() = locationSource.getCurrentLocation(this::updateCenterLocation)

    private fun updateCenterLocation(point: Point) {
        _centerLocation.value = point
    }
}