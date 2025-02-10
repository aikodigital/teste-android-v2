package hopeapps.dedev.sptrans.presentation.screens.bus_stop

import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.models.StaticPoint

data class BusStopState(
    var isLoading: Boolean = false,
    var busStopPrediction: List<BusPrediction> = emptyList(),
    val busStop: BusStop? = null,
    val errorMessage: String? = null,
    val busStopPoint: StaticPoint = StaticPoint(latitude = 0.0, longitude = 0.0, name = "")
)