package com.aiko.teste.sptrans.screens.bus_stop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiko.teste.sptrans.data.objects.BusLine
import com.aiko.teste.sptrans.data.objects.BusStopPrevisions
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BusStopScreenViewModel @Inject constructor(private val busStopsRepository: BusStopsRepository) :
    ViewModel() {
    private val _busStopLines = MutableLiveData<List<BusLine>>()
    val busStopLines: LiveData<List<BusLine>> = _busStopLines

    fun getBusStopPrevisions(busStopCode: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                busStopsRepository.getBusStopPrevisions(busStopCode)
            }
            if (result.isSuccess) {
                val data =
                    result.getOrDefault(BusStopPrevisions(null))
                val busStopLines: List<BusLine> = data.busStop?.busLines ?: emptyList()
                _busStopLines.value = busStopLines
            } else _busStopLines.value = emptyList()
        }
    }
}