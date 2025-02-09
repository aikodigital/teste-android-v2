package hopeapps.dedev.sptrans.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class MapsMarker(
    val title: String,
    val information: String = "",
    val location: Location
)