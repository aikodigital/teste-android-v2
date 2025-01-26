package br.com.danilo.aikotestebus.presentation.util.state

import br.com.danilo.aikotestebus.domain.model.BusesPosition

sealed class MapLocationBusState {
    data object Error : MapLocationBusState()
    data class Success(val items: BusesPosition) : MapLocationBusState()
    data object InitialState : MapLocationBusState()
}