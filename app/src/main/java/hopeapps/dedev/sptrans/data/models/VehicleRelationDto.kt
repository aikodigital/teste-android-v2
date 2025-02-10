package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class VehicleRelationDto(
    @SerializedName("p")
    val prefixVehicle: Int,
    @SerializedName("a")
    val accessible: Boolean,
    @SerializedName("ta")
    val lastUpdateTime: String,
    @SerializedName("py")
    val latitude: Double,
    @SerializedName("px")
    val longitude: Double
)