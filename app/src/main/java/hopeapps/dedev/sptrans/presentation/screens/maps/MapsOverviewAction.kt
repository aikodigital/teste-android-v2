package hopeapps.dedev.sptrans.presentation.screens.maps

sealed interface MapsOverviewAction {
    data object NavigateToBack: MapsOverviewAction
}