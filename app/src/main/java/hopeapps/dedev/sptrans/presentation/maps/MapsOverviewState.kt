package hopeapps.dedev.sptrans.presentation.maps

import hopeapps.dedev.sptrans.domain.models.Location
import hopeapps.dedev.sptrans.domain.models.MapPoint


data class OverviewMapsState(
    val actualLocation: Location = Location(lat = 0.0, long = 0.0),
    var dynamicPoints: List<MapPoint> = emptyList(),
    var staticPoints: List<MapPoint> = emptyList()
)
