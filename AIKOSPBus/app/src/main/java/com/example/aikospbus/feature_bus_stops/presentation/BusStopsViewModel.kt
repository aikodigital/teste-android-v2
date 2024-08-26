package com.example.aikospbus.feature_bus_stops.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel
import com.example.aikospbus.feature_bus_stops.domain.use_case.GetRemoteBusStopsUseCase
import com.example.aikospbus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusStopsViewModel @Inject constructor(
    private val getRemoteBusStopsUseCase: GetRemoteBusStopsUseCase
) : ViewModel() {

    private val _busDtoStopsDataModel = MutableLiveData<List<BusStopsModel>?>()

    val busDtoStopsDataModel: MutableLiveData<List<BusStopsModel>?>
        get() = _busDtoStopsDataModel

    fun getRemoteBusStopsData(cookie: String, searchTerms: String) = viewModelScope.launch {
        getRemoteBusStopsUseCase(cookie, searchTerms).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _busDtoStopsDataModel.value =
                        result.data
                }

                is Resource.Error -> {
                    _busDtoStopsDataModel.value = result.data
                }

                is Resource.Loading -> {
                    _busDtoStopsDataModel.value = result.data
                }
            }
        }.launchIn(this)
    }
}