package com.matreis.teste.sptrans.domain.model

import com.google.gson.annotations.SerializedName

data class VehiclePosition(
    @SerializedName("hr" ) var hr : String?      = null,
    @SerializedName("l"  ) var linesWithVehicles  : ArrayList<LineWithVehicles> = arrayListOf()
)
