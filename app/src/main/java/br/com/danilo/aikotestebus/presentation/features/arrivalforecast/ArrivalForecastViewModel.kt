package br.com.danilo.aikotestebus.presentation.features.arrivalforecast

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.ArrivalForecastUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.state.ArrivalForecastState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArrivalForecastViewModel(
    private val useCase: ArrivalForecastUseCase
) : BaseViewModel<ArrivalForecastState>(ArrivalForecastState.Loading) {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun fetchArrivalForecast(idStop: Int, idLine: Int) {
        viewModelScope.launch {
            uiStateAccess.value = ArrivalForecastState.Loading
            useCase.getArrivalForecastTime(idStop, idLine).collect { result ->
                result.fold(
                    onSuccess = { data ->
                        uiStateAccess.value = ArrivalForecastState.Success(data)
                    },
                    onFailure = {
                        uiStateAccess.value = ArrivalForecastState.Error
                    }
                )
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
}