package hopeapps.dedev.sptrans.presentation.screens.search

import androidx.annotation.OpenForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hopeapps.dedev.sptrans.domain.usecase.AuthUseCase
import hopeapps.dedev.sptrans.domain.usecase.BusLineUseCase
import hopeapps.dedev.sptrans.domain.usecase.BusStopUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val busLineUseCase: BusLineUseCase,
    private val authUseCase: AuthUseCase,
    private val searchBusStopUseCase: BusStopUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchScreenState())
        private set

    private val searchQueryFlow = MutableSharedFlow<String>(replay = 1)

    init {
        viewModelScope.launch {
            authenticateUser()
            searchQueryFlow
                .debounce(500)
                .collectLatest { query ->
                    executeSearch(query)
                }
        }
    }

    fun onAction(action: SearchScreenAction) {
        when (action) {
            is SearchScreenAction.SearchItems -> searchItems(action.query)
            is SearchScreenAction.ClickToSwitchTab -> updateSearchTab(action.searchBusStop)
            else -> { }
        }
    }

    @OpenForTesting
    suspend fun authenticateUser() {
        state = state.copy(isLoading = true)
        val result = authUseCase.invoke()
        if (result.isSuccess) {
            searchItems("")
        } else {
            state = state.copy(isLoading = false, errorMessage = "Authentication failed")
        }
    }

    @OpenForTesting
    fun updateSearchTab(isBusStop: Boolean) {
        state = state.copy(searchBusStop = isBusStop)
    }

    @OpenForTesting
    fun searchItems(query: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            searchQueryFlow.emit(query)
        }
    }

    private suspend fun executeSearch(query: String) {
        if (state.searchBusStop) {
            val result = searchBusStopUseCase(query)
            result.fold(
                onSuccess = {
                    state = state.copy(busStopItems = it, isLoading = false)
                },
                onFailure = {
                    state = state.copy(errorMessage = it.message, isLoading = false)
                }
            )
        } else {
            val result = busLineUseCase(query)
            result.fold(
                onSuccess = {
                    state = state.copy(busLinesItems = it, isLoading = false)
                },
                onFailure = {
                    state = state.copy(errorMessage = it.message, isLoading = false)
                }
            )
        }
    }
}
