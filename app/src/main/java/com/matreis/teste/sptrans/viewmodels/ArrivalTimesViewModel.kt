package com.matreis.teste.sptrans.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.domain.model.TimeWithBusStop
import com.matreis.teste.sptrans.domain.usecase.GetArrivalTimeUseCase
import com.matreis.teste.sptrans.helper.CoroutineDispatcherType.IO
import com.matreis.teste.sptrans.helper.Dispatcher
import com.matreis.teste.sptrans.helper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArrivalTimesViewModel @Inject constructor(
    private val getArrivalTimesUseCase: GetArrivalTimeUseCase,
    @Dispatcher(IO) private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var selectedLine: Long? = 0L

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<Event<Int>>()
    val error: LiveData<Event<Int>> get() = _error

    private val _timeWithBusStop = MutableLiveData<TimeWithBusStop>()
    val timeWithBusStop: LiveData<TimeWithBusStop> get() = _timeWithBusStop

    fun getArrivalTimes(stopCode: Long) {
        _isLoading.value = true
        viewModelScope.launch(coroutineDispatcher) {
            try {
                val response = getArrivalTimesUseCase.getByBusStop(stopCode)
                if (response.isSuccessful) {
                    _isLoading.postValue(false)
                    _timeWithBusStop.postValue(response.body())
                } else {
                    _isLoading.postValue(false)
                    _error.postValue(Event(R.string.error_get_arrival_times))
                }
            }catch (e: Exception) {
                _isLoading.postValue(false)
                _error.postValue(Event(R.string.error_get_arrival_times))
                e.printStackTrace()
            }
        }
    }

    fun setSelectedLine(lineCode: Long) {
        selectedLine = lineCode
    }


}