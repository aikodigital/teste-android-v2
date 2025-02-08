package hopeapps.dedev.sptrans.presentation.search

import hopeapps.dedev.sptrans.data.model.BusLine
import hopeapps.dedev.sptrans.data.model.BusStop

data class SearchScreenState(
    var searchBusLines: Boolean = true,
    var isLoading: Boolean = false,
    var busLinesItems: List<BusLine> = emptyList(),
    var busStopItems: List<BusStop> = emptyList(),
    val errorMessage: String? = null,
)