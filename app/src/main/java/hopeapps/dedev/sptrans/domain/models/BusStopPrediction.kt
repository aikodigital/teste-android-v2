package hopeapps.dedev.sptrans.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class BusPrediction(
    val idLine: Int,
    val destination: String,
    val origin: String,
    val px: Double,
    val py: Double,
    val lastUpdateTime: String,
    val predictionTime: String,
    val accessibleVehicle: Boolean,
    val firstToSecondTerminal: Boolean
)
