package com.example.aikospbus.feature_bus_lines.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import com.example.aikospbus.feature_bus_lines.domain.use_case.GetRemoteBusLinesUseCase
import com.example.aikospbus.feature_bus_lines.domain.use_case.InsertBusLinesUseCase
import com.example.aikospbus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusLinesViewModel @Inject constructor(
    private val insertBusLinesUseCase: InsertBusLinesUseCase,
    private val getRemoteBusLinesUseCase: GetRemoteBusLinesUseCase
) : ViewModel() {

    private val _busDtoLinesDataModel = MutableLiveData<BusLinesModel?>()

    val busDtoLinesDataModel: MutableLiveData<BusLinesModel?>
        get() = _busDtoLinesDataModel

    fun getRemoteBuslinesData(cookie: String, searchTerms: String) = viewModelScope.launch {
        getRemoteBusLinesUseCase(cookie,searchTerms).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _busDtoLinesDataModel.value = result.data
                    println("RESULTADO DA API: ${result.data?.tipo}")
                    println("RESULTADO DA API: ${result.data?.sentido}")
                    println("API SUCCESS")
                }
                is Resource.Error -> {
                    _busDtoLinesDataModel.value = result.data
                    println("API ERROR")
                }
                is Resource.Loading -> {
                    _busDtoLinesDataModel.value = result.data
                    println("API LOADING")
                }
            }
        }.launchIn(this)
    }
}