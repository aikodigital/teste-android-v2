package com.aiko.teste.sptrans.screens.bus_stop

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiko.teste.sptrans.data.objects.BusStopPrevisions
import com.aiko.teste.sptrans.data.objects.Line
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BusStopScreenViewModel @Inject constructor(private val busStopsRepository: BusStopsRepository) :
    ViewModel() {

    private val _busStopLines = MutableLiveData<List<Line>>()
    val busStopLines: LiveData<List<Line>> = _busStopLines
    fun getBusStopPrevisions(busStopCode: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                Log.d("BusStopScreenViewModel", "stopCode = $busStopCode")
                busStopsRepository.getBusStopPrevisions(busStopCode)
            }
            if (result.isSuccess) {
                val data =
                    result.getOrDefault(BusStopPrevisions(null))
                val busStopLines: List<Line> = data.busStop?.lines ?: emptyList()
                _busStopLines.value = busStopLines
            } else _busStopLines.value = emptyList()
        }
    }
}