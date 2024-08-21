package com.aiko.teste.sptrans.screens.map

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiko.teste.sptrans.data.LocationSource
import com.mapbox.geojson.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(private val locationSource: LocationSource) :
    ViewModel() {

    private val _centerLocation = MutableLiveData<Point>()
    val centerLocation: LiveData<Point> = _centerLocation
    fun getCenterLocation() = locationSource.getCurrentLocation(this::updateCenterLocation)

    private fun updateCenterLocation(point: Point) {
        _centerLocation.value = point
    }
}