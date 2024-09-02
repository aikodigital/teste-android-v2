package com.example.testeaiko.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testeaiko.datamodels.ModelLinha
import com.example.testeaiko.datamodels.ModelParada
import com.example.testeaiko.datamodels.ModelPosicao
import com.example.testeaiko.datamodels.ModelPrevisaoParada
import com.example.testeaiko.datamodels.ModelRoute
import com.example.testeaiko.repositories.RoutesApiRepository
import com.example.testeaiko.repositories.TransitApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val transitApiRepository: TransitApiRepository,
                                       private val routesApiRepository: RoutesApiRepository) :
    ViewModel() {
    private val _vehicleInfo = MutableLiveData<Result<Response<ModelPosicao>>?>()
    val vehicleInfo: MutableLiveData<Result<Response<ModelPosicao>>?> = _vehicleInfo
    private val _stopsInfo = MutableLiveData<Result<Response<List<ModelParada>>>?>()
    val stopsInfo: MutableLiveData<Result<Response<List<ModelParada>>>?> = _stopsInfo
    private val _stopArrivalsInfo = MutableLiveData<Result<Response<ModelPrevisaoParada>>?>()
    val stopArrivalsInfo: MutableLiveData<Result<Response<ModelPrevisaoParada>>?> =
        _stopArrivalsInfo
    private val _lineSearchInfo = MutableLiveData<Result<Response<List<ModelLinha>>>?>()
    val lineSearchInfo: MutableLiveData<Result<Response<List<ModelLinha>>>?> = _lineSearchInfo
    private val _routesInfo = MutableLiveData<Result<Response<ModelRoute>>?>()
    val routesInfo: MutableLiveData<Result<Response<ModelRoute>>?> = _routesInfo
    fun getTransitInfo() {
        transitApiRepository.getPosicao { result ->
            _vehicleInfo.postValue(result)
        }
    }

    fun getStopsInfo() {
        transitApiRepository.getPontos { result ->
            _stopsInfo.postValue(result)
        }
    }

    fun searchStop(stopId: Int) {
        transitApiRepository.getPrevisaoParada(stopId) { result ->
            _stopArrivalsInfo.postValue(result)
        }
    }

    fun searchLine(query: String) {
        transitApiRepository.getLinhaBuscar(query) { result ->
            _lineSearchInfo.postValue(result)
        }
    }

    fun resetTransitData() {
        _vehicleInfo.value = null
        _stopsInfo.value = null
    }

    fun getRoutes(origin: String, destination: String) {
        routesApiRepository.getRoutes(origin, destination) { result ->
            _routesInfo.postValue(result)
        }
    }
}