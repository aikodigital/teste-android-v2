package br.com.danilo.aikotestebus.presentation.util.state

import br.com.danilo.aikotestebus.domain.model.LineDetail

sealed class LineBusDetailState {
    data object Loading : LineBusDetailState()
    data object Error : LineBusDetailState()
    data object ShowScreen : LineBusDetailState()
    data class Success(val items: List<LineDetail>): LineBusDetailState()
}