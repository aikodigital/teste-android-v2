package com.matreis.teste.sptrans.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.Vehicle
import com.matreis.teste.sptrans.domain.usecase.GetArrivalTimeUseCase
import com.matreis.teste.sptrans.domain.usecase.GetBusStopUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusStopViewModel @Inject constructor(
    private val getBusStopUseCase: GetBusStopUseCase,
    private val getArrivalTimeUseCase: GetArrivalTimeUseCase
) : ViewModel() {

    private val _busStops = MutableLiveData<List<BusStop>>()
    val busStops: LiveData<List<BusStop>> get() = _busStops

    private val _arrivalTimes = MutableLiveData<List<Vehicle>>()
    val arrivalTimes: LiveData<List<Vehicle>> get() = _arrivalTimes

    fun getBusStop(term: String) {
        viewModelScope.launch {
            //val times = ArrayList<Vehicle>()
           try {
               val response = getBusStopUseCase(term)
               if (response.isSuccessful) {
                   val stops = response.body() ?: emptyList()
                   stops.forEach {
                       val arrivalTime = getArrivalTimeByStop(it.stopCod!!)
                       it.vehicles.addAll(arrivalTime)
                       //times.addAll(arrivalTime)
                   }
                   //_arrivalTimes.value = times
                   _busStops.value = stops
               }else {
                   Log.e("BusStopViewModel", "getBusStop: ${response.errorBody()}")
               }
           }catch (e: Exception) {
               e.printStackTrace()
           }
        }

    }

    private suspend fun getArrivalTimeByStop(stopCode: Long): List<Vehicle> {
        val response = getArrivalTimeUseCase.getByBusStop(stopCode)
        if (response.isSuccessful) {
            return response.body()?.busStop?.lines?.flatMap { line -> line.vehicles.map { it.copy(lineSign = line.fullSign) } } ?: emptyList()
        }
        return emptyList()
    }
}
