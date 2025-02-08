package hopeapps.dedev.sptrans.presentation.line_bus

sealed interface LineBusAction {
    data object ViewInMapClick: LineBusAction
}