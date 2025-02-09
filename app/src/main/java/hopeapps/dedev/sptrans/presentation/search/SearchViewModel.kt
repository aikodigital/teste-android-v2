package hopeapps.dedev.sptrans.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hopeapps.dedev.sptrans.domain.usecase.AuthUseCase
import hopeapps.dedev.sptrans.domain.usecase.BusStopUseCase
import hopeapps.dedev.sptrans.domain.usecase.BusLineUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val busLineUseCase: BusLineUseCase,
    private val authUseCase: AuthUseCase,
    private val searchBusStopUseCase: BusStopUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchScreenState())
        private set

    init {
        viewModelScope.launch {
            authenticateUser()
        }
    }

    fun onAction(action: SearchScreenAction) {
        when (action) {
            is SearchScreenAction.SearchItems -> searchItems(action.query)
            is SearchScreenAction.ClickToSwitchTab -> state = state.copy(searchBusLines = action.searchBusLines)
            else -> {}
        }
    }

    private suspend fun authenticateUser() {
        val result = authUseCase()
        if (result.isSuccess) {
            searchItems(query = "1")
        } else {
            state = state.copy(isLoading = false)
        }
    }


    private fun searchItems(query: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            if (state.searchBusLines) {
                val result = busLineUseCase(query)
                result.fold(
                    onSuccess = { state = state.copy(busLinesItems = it, isLoading = false) },
                    onFailure = { state = state.copy(errorMessage = it.message, isLoading = false) }
                )
            } else {
                val result = searchBusStopUseCase(query)
                result.fold(
                    onSuccess = { state = state.copy(busStopItems = it, isLoading = false) },
                    onFailure = { state = state.copy(errorMessage = it.message, isLoading = false) }
                )
            }
        }
    }
}