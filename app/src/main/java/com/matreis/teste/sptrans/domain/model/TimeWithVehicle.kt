package com.matreis.teste.sptrans.domain.model

import com.google.gson.annotations.SerializedName

data class TimeWithVehicle(
    @SerializedName("hr" ) var hr : String?       = null,
    @SerializedName("vs" ) var vehicles : ArrayList<Vehicle> = arrayListOf()
)