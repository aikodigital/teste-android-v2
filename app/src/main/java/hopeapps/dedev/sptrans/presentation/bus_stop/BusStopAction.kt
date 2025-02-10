package hopeapps.dedev.sptrans.presentation.bus_stop

import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.domain.models.StaticPoint

sealed interface BusStopAction {
    data class ViewInMapClick(val staticPoint: StaticPoint): BusStopAction
    data class ViewBusInMap(val dynamicPoint: DynamicPoint): BusStopAction
    data object NavigateBack: BusStopAction
}