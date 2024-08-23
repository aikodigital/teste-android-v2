package com.matreis.teste.sptrans.domain.model

import com.google.gson.annotations.SerializedName

data class BusStop(
    @SerializedName("cp") var stopCod: Long? = null,
    @SerializedName("np") var name: String? = null,
    @SerializedName("ed") var address: String? = null,
    @SerializedName("py") var lat: Double? = null,
    @SerializedName("px") var lng: Double? = null,
    @SerializedName("l") var lines: ArrayList<LineWithVehicles> = arrayListOf(),
    @SerializedName("vs") var vehicles: ArrayList<Vehicle> = arrayListOf()
)
