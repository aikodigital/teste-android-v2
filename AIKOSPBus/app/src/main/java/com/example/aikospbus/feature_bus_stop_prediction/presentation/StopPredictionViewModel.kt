package com.example.aikospbus.feature_bus_stop_prediction.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.VeiculosTestDto
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.VeiculoTestModel
import com.example.aikospbus.feature_bus_stop_prediction.domain.use_case.GetRemoteStopPredictionUseCase
import com.example.aikospbus.feature_bus_stop_prediction.domain.use_case.InsertStopPredictionUseCase
import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel
import com.example.aikospbus.feature_bus_stops.domain.use_case.GetRemoteBusStopsUseCase
import com.example.aikospbus.feature_bus_stops.domain.use_case.InsertBusStopsUseCase
import com.example.aikospbus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StopPredictionViewModel @Inject constructor(
    private val insertStopPrediction: InsertStopPredictionUseCase,
    private val getRemoteBusStopsPredictionUseCase: GetRemoteStopPredictionUseCase
): ViewModel() {

    var busStopPredictionLiveData: MutableLiveData<List<StopPredictionModel>?> = MutableLiveData()

    var veiculoListLiveData: MutableLiveData<List<VeiculosTestDto>?> = MutableLiveData()

    fun updateVeiculoList() {
        val stopPredictionList = busStopPredictionLiveData.value ?: return

        val veiculoList = stopPredictionList.flatMap { stopPrediction ->
            stopPrediction.parada.linhas.flatMap { linha ->
                linha.veiculos
            }
        }

        veiculoListLiveData.value = veiculoList
    }



    fun getRemoteBusStopsPredictionData(cookie: String, searchTerms: Int) = viewModelScope.launch {
        getRemoteBusStopsPredictionUseCase(cookie,searchTerms).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    busStopPredictionLiveData.value = result.data
                    updateVeiculoList()
                    println("API SUCCESS")
                }
                is Resource.Error -> {
                    busStopPredictionLiveData.value = result.data
                    println("API ERROR")
                }
                is Resource.Loading -> {
                    busStopPredictionLiveData.value = result.data
                    println("API LOADING")
                }
            }
        }.launchIn(this)
    }

}