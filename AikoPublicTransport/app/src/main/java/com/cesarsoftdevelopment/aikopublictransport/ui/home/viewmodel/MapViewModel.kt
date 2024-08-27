package com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarsoftdevelopment.aikopublictransport.data.model.EstimatedArrivalTime
import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetEstimatedArrivalTimesByStopUseCase
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.launch

class MapViewModel(
    private val application: Application,
    private val getEstimatedArrivalTimesByStopUseCase : GetEstimatedArrivalTimesByStopUseCase

) : ViewModel() {
    private val _estimatedArrivalTime : MutableLiveData<Resource<EstimatedArrivalTime>> = MutableLiveData()
    val estimatedArrivalTime : LiveData<Resource<EstimatedArrivalTime>> = _estimatedArrivalTime

    fun getEstimatedArrivalTime(stop : Long?) {
        _estimatedArrivalTime.postValue(Resource.Loading())

        viewModelScope.launch {
            try {
                val response = getEstimatedArrivalTimesByStopUseCase.invoke(stop)
                _estimatedArrivalTime.postValue(response)

            }catch (err : Exception) {
                _estimatedArrivalTime.postValue(Resource.Error(err.message.toString()))
            }
        }
    }


}