package hopeapps.dedev.sptrans.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class BusStop(
    val idCodeBusStop: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
)