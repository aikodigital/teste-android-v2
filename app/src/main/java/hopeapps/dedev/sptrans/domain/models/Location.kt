package hopeapps.dedev.sptrans.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class Location(
    val lat: Double,
    val long: Double
)