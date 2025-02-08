package hopeapps.dedev.sptrans.presentation.search

sealed interface SearchScreenAction {
    data object ClearSearchField : SearchScreenAction
    data object ClickListItem : SearchScreenAction
    data class ClickToSwitchTab(val searchBusLines: Boolean) : SearchScreenAction
    data class SearchItems(val query: String): SearchScreenAction
}