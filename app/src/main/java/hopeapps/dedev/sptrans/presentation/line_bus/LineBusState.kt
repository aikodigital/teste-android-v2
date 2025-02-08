package hopeapps.dedev.sptrans.presentation.line_bus

import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop

data class LineBusState(
    var isLoading: Boolean = false,
    var busStopItems: List<BusStop> = emptyList(),
    val busLine: BusLine? = null,
    val errorMessage: String? = null
)