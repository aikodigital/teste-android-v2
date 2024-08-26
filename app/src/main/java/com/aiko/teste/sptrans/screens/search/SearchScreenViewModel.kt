package com.aiko.teste.sptrans.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiko.teste.sptrans.data.objects.BusLine
import com.aiko.teste.sptrans.data.objects.BusStop
import com.aiko.teste.sptrans.data.repositories.BusLinesRepository
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val busStopsRepository: BusStopsRepository,
    private val busLinesRepository: BusLinesRepository
) : ViewModel() {
    private val _busStops = MutableLiveData<List<BusStop>>()
    val busStops: LiveData<List<BusStop>> = _busStops

    private val _busLines = MutableLiveData<List<BusLine>>()
    val busLines: LiveData<List<BusLine>> = _busLines

    fun search(query: String) {
        searchBusStops(query)
        searchBusLines(query)
    }

    private fun searchBusLines(query: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                busLinesRepository.getBusLines(query)
            }
            if (result.isSuccess) {
                val data = result.getOrDefault(emptyList())
                _busLines.value = data.filter { it.lineWay == 1 }
            }
        }
    }

    private fun searchBusStops(query: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                busStopsRepository.getBusStops(query)
            }
            if (result.isSuccess) {
                val data = result.getOrDefault(emptyList())
                _busStops.value = data
            }
        }
    }
}