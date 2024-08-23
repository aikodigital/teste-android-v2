package com.matreis.teste.sptrans.domain.model

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("p") var prefix: String? = null,
    @SerializedName("t") var estimatedArrivalTime: String? = null,
    @SerializedName("a") var isAccessible: Boolean? = null,
    @SerializedName("py") var lat: Double? = null,
    @SerializedName("px") var lng: Double? = null,
    var lineSign: String? = null,
)
