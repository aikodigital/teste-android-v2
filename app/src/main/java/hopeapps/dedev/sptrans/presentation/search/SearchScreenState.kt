package hopeapps.dedev.sptrans.presentation.search

import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop

data class SearchScreenState(
    var searchBusStop: Boolean = true,
    var isLoading: Boolean = false,
    var busLinesItems: List<BusLine> = emptyList(),
    var busStopItems: List<BusStop> = emptyList(),
    val errorMessage: String? = null,
)