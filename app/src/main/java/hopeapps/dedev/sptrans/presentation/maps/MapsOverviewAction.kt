package hopeapps.dedev.sptrans.presentation.maps

sealed interface MapsOverviewAction {
    data object NavigateToBack: MapsOverviewAction
}