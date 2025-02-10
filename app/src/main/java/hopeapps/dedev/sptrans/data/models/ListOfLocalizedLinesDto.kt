package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ListOfLocalizedLinesDto(
    @SerializedName("c")
    val sign: String,
    @SerializedName("cl")
    val lineCode: Int,
    @SerializedName("sl")
    val lineWay: Int,
    @SerializedName("lt0")
    val destination: String,
    @SerializedName("lt1")
    val origin: String,
    @SerializedName("qv")
    val numberVehiclesLocated: String,
    @SerializedName("vs")
    val vehicleList: List<ListOfVehiclesLocatedDto>
)
