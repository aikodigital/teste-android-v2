package com.aiko.teste.sptrans.screens.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiko.teste.sptrans.data.objects.BusStop
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import com.aiko.teste.sptrans.data.repositories.LocationRepository
import com.mapbox.geojson.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val locationSource: LocationRepository,
    private val busStopsRepository: BusStopsRepository
) : ViewModel() {
    private val _centerLocation = MutableLiveData<Point>()
    val centerLocation: LiveData<Point> = _centerLocation

    private val _busStopsPoints = MutableLiveData<List<Point>>()
    val busStopsPoints: LiveData<List<Point>> = _busStopsPoints

    private val busStops: ArrayList<BusStop> = arrayListOf()

    fun getMapPoints() {
        getCenterLocation()
        getBusStops()
        //getBusPositions()
    }

    fun getBusStopFromPoint(point: Point): BusStop {
        val position = _busStopsPoints.value!!.indexOf(point)
        return busStops[position]
    }

    private fun updateCenterLocation(point: Point) {
        _centerLocation.value = point
    }

    private fun getCenterLocation() = locationSource.getCurrentLocation(this::updateCenterLocation)

    private fun getBusStops() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                busStopsRepository.getBusStops()
            }
            if (result.isSuccess) {
                val data = result.getOrDefault(emptyList())
                val busStopsPoints = data.map { busStop ->
                    Point.fromLngLat(busStop.longitude, busStop.latitude)
                }
                _busStopsPoints.value = busStopsPoints
                busStops.addAll(data)
            }
        }
    }

    /*private fun getBusPositions() {
        val call = apiService.getBusPositions()
        call.enqueue(object : Callback<BusPositions> {
            override fun onResponse(call: Call<BusPositions>, response: Response<BusPositions>) {
                if (response.isSuccessful) {
                    response.body()?.let { busPositions ->
                        val busPoints: List<Point>
                        busPositions.lines.forEach { bus ->
                            bus.positions.forEach()
                        }
                        _busPositions.value = Result.success(busPositions)
                    }?: run {
                        _busPositions.value = Result.failure(Exception("Empty body response"))
                    }
                } else {
                    _busPositions.value =
                        Result.failure(Exception("API error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<BusPositions>, t: Throwable) {
                _busPositions.value = Result.failure(t)
            }

        })
    }*/
}