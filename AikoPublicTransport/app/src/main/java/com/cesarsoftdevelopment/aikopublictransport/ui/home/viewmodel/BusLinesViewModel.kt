package com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetBusLinesUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetStopsByLineUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetVehiclesPositionByLineUseCase
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class BusLinesViewModel @Inject constructor(
    private val getBusLinesUseCase: GetBusLinesUseCase,
    private val getVehiclesPositionByLineUseCase: GetVehiclesPositionByLineUseCase,
    private val getStopsByLineUseCase: GetStopsByLineUseCase
) : ViewModel() {

    private val _busLines : MutableLiveData<Resource<List<BusLineItem>>> = MutableLiveData()
    val busLines : LiveData<Resource<List<BusLineItem>>> = _busLines

    private val _selectedLineCode : MutableLiveData<Int> = MutableLiveData()
    val selectedLineCode : LiveData<Int> = _selectedLineCode

    fun getBusLines(termsSearch : String) {
        _busLines.postValue(Resource.Loading())

        viewModelScope.launch {
            try {
                val response = getBusLinesUseCase.invoke(termsSearch)
                _busLines.postValue(response)

            }catch (err : Exception) {
                _busLines.postValue(Resource.Error(err.message.toString()))
            }
        }
    }

    fun setSelectedLineCode(lineCode : Int) {
        _selectedLineCode.postValue(lineCode)
    }


}