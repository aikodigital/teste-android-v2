package com.matreis.teste.sptrans.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.Line
import com.matreis.teste.sptrans.domain.model.LineWithVehicles
import com.matreis.teste.sptrans.domain.model.Vehicle
import com.matreis.teste.sptrans.domain.usecase.GetArrivalTimeUseCase
import com.matreis.teste.sptrans.domain.usecase.GetBusStopUseCase
import com.matreis.teste.sptrans.helper.CoroutineDispatcherType.IO
import com.matreis.teste.sptrans.helper.Dispatcher
import com.matreis.teste.sptrans.helper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusStopViewModel @Inject constructor(
    private val getBusStopUseCase: GetBusStopUseCase,
    private val getArrivalTimeUseCase: GetArrivalTimeUseCase,
    @Dispatcher(IO) private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _busStops = MutableLiveData<List<BusStop>>()
    val busStops: LiveData<List<BusStop>> get() = _busStops

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<Event<Int>>()
    val error: LiveData<Event<Int>> get() = _error

    fun getBusStop(term: String) {
        _isLoading.value = true
        viewModelScope.launch(coroutineDispatcher) {
           try {
               val response = getBusStopUseCase(term)
               if (response.isSuccessful) {
                   val stops = response.body() ?: emptyList()
                   stops.forEach {
                       val arrivalTime = getArrivalTimeByStop(it.stopCod!!)
                       it.vehicles.addAll(arrivalTime)
                   }
                   _isLoading.value = false
                   _busStops.value = stops
               }else {
                   _isLoading.value = false
                   _error.postValue(Event(R.string.no_bus_stop_found))
                   Log.e("BusStopViewModel", "getBusStop: ${response.errorBody()}")
               }
           }catch (e: Exception) {
               _isLoading.value = false
               _error.postValue(Event(R.string.error_get_bus_stop))
               e.printStackTrace()
           }
        }

    }

    private suspend fun getArrivalTimeByStop(stopCode: Long): List<Vehicle> {
        val response = getArrivalTimeUseCase.getByBusStop(stopCode)
        if (response.isSuccessful) {
            return response.body()?.busStop?.lines?.flatMap { line -> line.vehicles.map { it.copy(line = buildLine(line)) } } ?: emptyList()
        }
        return emptyList()
    }

    private fun buildLine(line: LineWithVehicles): Line? {
        return Line(
            codLine = line.lineCode,
            fullSign = line.fullSign,
            direction = line.direction,
            mainTerminalDescription = line.destinationSign,
            secondaryTerminalDescription = line.originSign
        )
    }
}
