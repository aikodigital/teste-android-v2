package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BusLineDto(
    @SerializedName("cl") val lineId: Int?,
    @SerializedName("lc") val isCircular: Boolean?,
    @SerializedName("lt") val firstLabelNumber: String?,
    @SerializedName("tl") val secondLabelNumber: Int?,
    @SerializedName("sl") val sense: Int?,
    @SerializedName("tp") val mainTerminal: String?,
    @SerializedName("ts") val secondaryTerminal: String?
)