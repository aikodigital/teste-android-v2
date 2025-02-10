package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName

data class LineVehiclesPositionDto(
    @SerializedName("hr")
    val hour: String,
    @SerializedName("vs")
    val vehicles: List<VehicleRelationDto>
)