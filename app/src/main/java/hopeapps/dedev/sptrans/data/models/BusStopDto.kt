package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class BusStopDto(
    @SerializedName("cp")
    val idCodeBusStop: Int,
    @SerializedName("np")
    val name: String,
    @SerializedName("ed")
    val address: String,
    @SerializedName("py")
    val latitude: Double,
    @SerializedName("px")
    val longitude: Double
)
