package br.com.danilo.aikotestebus.presentation.features.lines

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.LineBusDetailUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.state.LineBusDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LineBusDetailsViewModel(
    private val useCase: LineBusDetailUseCase
) : BaseViewModel<LineBusDetailState>(LineBusDetailState.ShowScreen) {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun fetchLines(query: String) {
        viewModelScope.launch {
            uiStateAccess.value = LineBusDetailState.Loading
            useCase.getBusLine(query).collect { result ->
                result.fold(
                    onSuccess = { data ->
                        uiStateAccess.value = LineBusDetailState.Success(data)
                    },
                    onFailure = {
                        uiStateAccess.value = LineBusDetailState.Error
                    }
                )
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        fetchLines(query)
    }
}