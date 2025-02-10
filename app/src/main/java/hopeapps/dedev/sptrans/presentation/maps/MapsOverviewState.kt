package hopeapps.dedev.sptrans.presentation.maps

import hopeapps.dedev.sptrans.domain.models.Location
import hopeapps.dedev.sptrans.domain.models.MapPoint
import hopeapps.dedev.sptrans.domain.models.StaticPoint


data class OverviewMapsState(
    val actualLocation: Location = Location(lat = -23.550520, long = -46.633309),
    val focusLocation: Location? = null,
    val busStopLocation: StaticPoint? = null,
    var dynamicPoints: List<MapPoint> = emptyList(),
    var staticPoints: List<MapPoint> = emptyList(),
    val errorMessage: String = ""
)
