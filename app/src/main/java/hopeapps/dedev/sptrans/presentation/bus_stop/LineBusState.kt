package hopeapps.dedev.sptrans.presentation.bus_stop

import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop

data class BusStopState(
    var isLoading: Boolean = false,
    var busStopPrediction: List<BusPrediction> = emptyList(),
    val busStop: BusStop? = null,
    val errorMessage: String? = null
)