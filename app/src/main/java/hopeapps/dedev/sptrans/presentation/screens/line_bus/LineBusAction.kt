package hopeapps.dedev.sptrans.presentation.screens.line_bus

import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.models.DynamicPoint

sealed interface LineBusAction {
    data class ViewInMapClick(val dynamicPoint: List<DynamicPoint>): LineBusAction
    data object NavigateBack : LineBusAction
    data class NavigateToBusStop(val busStop: BusStop) : LineBusAction
}