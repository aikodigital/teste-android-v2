package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LinesVehicleRelation(
    @SerializedName("c")
    val fullSign: String,
    @SerializedName("cl")
    val lineCode: Int,
    @SerializedName("sl")
    val senseOperation: Int,
    @SerializedName("lt0")
    val destination: String,
    @SerializedName("lt1")
    val origin: String,
    @SerializedName("qv")
    val quantityLocalizedVehicles: Int,
    @SerializedName("vs")
    val vehicles: List<VehicleRelationDto>
)