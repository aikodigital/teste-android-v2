package br.com.aiko.projetoolhovivo.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastByLine
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastByStop
import br.com.aiko.projetoolhovivo.domain.usecase.forecast.ForecastUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastViewModel @Inject constructor(
    private val forecastUseCase: ForecastUseCase
) : ViewModel() {
    var token: String = ""

    private var _isLoading = MutableLiveData(false)
    private val _error = MutableLiveData<String?>(null)
    private val _forecast_by_stop = MutableLiveData<ForecastByStop?>(null)
    private val _forecast_by_line = MutableLiveData<ForecastByLine?>(null)

    val isLoading: LiveData<Boolean> get() = _isLoading
    val error: LiveData<String?> get() = _error
    val forecastByStop: LiveData<ForecastByStop?> get() = _forecast_by_stop
    val forecastByLine: LiveData<ForecastByLine?> get() = _forecast_by_line

    fun getForecastByStopCode(codeStop: Int) = viewModelScope.launch {
        _isLoading.postValue(true)
        forecastUseCase.getForecastByStopCode(token, codeStop).onSuccess { forecast ->
            _forecast_by_stop.postValue(forecast)
            _isLoading.postValue(false)
        }.onFailure { fail ->
            run {
                _isLoading.postValue(false)
                _error.postValue(fail.stackTraceToString())
            }
        }
    }
    fun getForecastByLineCode(codeLine: Int) = viewModelScope.launch {
        _isLoading.postValue(true)
        forecastUseCase.getForecastByLineCode(token, codeLine).onSuccess { forecast ->
            _forecast_by_line.postValue(forecast)
            _isLoading.postValue(false)
        }.onFailure { fail ->
            run {
                _isLoading.postValue(false)
                _error.postValue(fail.stackTraceToString())
            }
        }
    }
}