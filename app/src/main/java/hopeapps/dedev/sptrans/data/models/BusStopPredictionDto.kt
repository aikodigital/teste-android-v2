package hopeapps.dedev.sptrans.data.models

import com.google.gson.annotations.SerializedName

data class BusStopPredictionDto(
    @SerializedName("hr")
    val hour: String,
    @SerializedName("p")
    val busStop: BusStopForecastModel
)
