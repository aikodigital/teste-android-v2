package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LinesLocationsDto(
    @SerializedName("hr")
    val time: String,
    @SerializedName("l")
    val vehiclesPerLine: List<LinesVehicleRelation>
)




