package hopeapps.dedev.sptrans.presentation.screens.search

import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop

sealed interface SearchScreenAction {
    data object NavigateToMaps : SearchScreenAction
    data class ClickListBusLine(val busLine: BusLine) : SearchScreenAction
    data class ClickListBusStop(val busStop: BusStop) : SearchScreenAction
    data class ClickToSwitchTab(val searchBusStop: Boolean) : SearchScreenAction
    data class SearchItems(val query: String) : SearchScreenAction
}