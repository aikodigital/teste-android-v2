package br.com.danilo.aikotestebus.presentation.util.state

import br.com.danilo.aikotestebus.domain.model.ArrivalForecast

sealed class ArrivalForecastState {
    data object Loading : ArrivalForecastState()
    data object Error : ArrivalForecastState()
    data class Success(val item: ArrivalForecast) : ArrivalForecastState()
}
