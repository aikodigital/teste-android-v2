package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ListOfVehiclesLocated(
    @SerializedName("p")
    val prefixVehicle: Int,
    @SerializedName("t")
    val arrivalForecast: String,
    @SerializedName("a")
    val accessibleForDisability: Boolean,
    @SerializedName("ta")
    val hourLastLocation: String,
    @SerializedName("py")
    val py: Double,
    @SerializedName("px")
    val px: Double
)