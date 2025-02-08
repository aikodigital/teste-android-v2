package hopeapps.dedev.sptrans.presentation.search

import hopeapps.dedev.sptrans.data.model.BusLine
import hopeapps.dedev.sptrans.data.model.BusStop

sealed interface SearchScreenAction {
    data object ClearSearchField : SearchScreenAction
    data class ClickListBusLine(val busLine: BusLine) : SearchScreenAction
    data class ClickListBusStop(val busStop: BusStop) : SearchScreenAction
    data class ClickToSwitchTab(val searchBusLines: Boolean) : SearchScreenAction
    data class SearchItems(val query: String): SearchScreenAction
}