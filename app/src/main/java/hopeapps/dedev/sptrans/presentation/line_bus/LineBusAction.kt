package hopeapps.dedev.sptrans.presentation.line_bus

import hopeapps.dedev.sptrans.domain.models.DynamicPoint

sealed interface LineBusAction {
    data class ViewInMapClick(val dynamicPoint: List<DynamicPoint>): LineBusAction
    data object NavigateBack : LineBusAction
}