package br.com.danilo.aikotestebus.presentation.features.stopbyline

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.BusStopByLineUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.state.BusStopByLineState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BusStopByLineViewModel(
    private val busStopByLineUseCase: BusStopByLineUseCase
) : BaseViewModel<BusStopByLineState>(BusStopByLineState.Loading) {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun fetchBusStopByLine(idLine: Int) {
        viewModelScope.launch {
            busStopByLineUseCase.getBusStopByLine(idLine).collect { result ->
                result.fold(
                    onSuccess = { data ->
                        uiStateAccess.value = BusStopByLineState.Success(data)
                    },
                    onFailure = {
                        uiStateAccess.value = BusStopByLineState.Error
                    }
                )
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
}