package hopeapps.dedev.sptrans.presentation.bus_stop

sealed interface BusStopAction {
    data object ViewInMapClick: BusStopAction
}