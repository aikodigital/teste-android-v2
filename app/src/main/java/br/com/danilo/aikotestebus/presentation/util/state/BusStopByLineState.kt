package br.com.danilo.aikotestebus.presentation.util.state

import br.com.danilo.aikotestebus.domain.model.StopDetail

sealed class BusStopByLineState {
    data object Loading : BusStopByLineState()
    data object Error : BusStopByLineState()
    data class Success(val items: List<StopDetail> ) : BusStopByLineState()
}