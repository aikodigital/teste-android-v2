package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class BusStopForecastModelDto(
    @SerializedName("cp")
    val id: Int,
    @SerializedName("np")
    val name: String,
    @SerializedName("py")
    val latitude: Double,
    @SerializedName("px")
    val longitude: Double,
    @SerializedName("l")
    val listOfLinesFound: List<ListOfLocalizedLinesDto>
)