package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.infra.ApiService
import com.example.myapplication.infra.Retrofit
import com.example.myapplication.infra.Token
import com.example.myapplication.model.BusStop
import com.example.myapplication.model.Lines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LineViewModel : ViewModel() {

    private val _listOfLine = MutableLiveData<MutableList<Lines>>()
    val lineList: LiveData<MutableList<Lines>> get() = _listOfLine
    val apiService: ApiService = Retrofit().apiService
    private val _busStops = MutableLiveData<List<BusStop>>()
    val busStops: LiveData<List<BusStop>> get() = _busStops

    fun setBusStops(stops: List<BusStop>) {
        _busStops.value = stops
    }
    init {
        _listOfLine.value = mutableListOf()
    }

   fun fetchBusLine(termoBusca: String){
        viewModelScope.launch {
            val lineList = getLines(termoBusca)
            _listOfLine.value = lineList.toMutableList()
        }
    }

    // Busca códigos de linhas de ônibus
    private suspend fun getLines(termoBusca: String): List<Lines> {
        return withContext(Dispatchers.IO) {
            try {
                val response = Retrofit().apiService.searchBusLine("Bearer ${Token.valueApi.TOKEN}", termoBusca)
                response
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}