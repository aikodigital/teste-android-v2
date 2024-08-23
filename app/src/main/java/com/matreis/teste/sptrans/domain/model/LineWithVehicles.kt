package com.matreis.teste.sptrans.domain.model

import com.google.gson.annotations.SerializedName

data class LineWithVehicles(
    @SerializedName("c"   ) var fullSign   : String?       = null,
    @SerializedName("cl"  ) var lineCode  : Int?          = null,
    @SerializedName("sl"  ) var direction  : Int?          = null,
    @SerializedName("lt0" ) var destinationSign : String?       = null,
    @SerializedName("lt1" ) var originSign : String?       = null,
    @SerializedName("qv"  ) var qntVehicles  : Int?          = null,
    @SerializedName("vs"  ) var vehicles  : ArrayList<Vehicle> = arrayListOf()
)
