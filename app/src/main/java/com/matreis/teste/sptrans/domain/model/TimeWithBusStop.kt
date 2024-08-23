package com.matreis.teste.sptrans.domain.model

import com.google.gson.annotations.SerializedName

data class TimeWithBusStop(
    @SerializedName("hr") var hr: String? = null,
    @SerializedName("p") var busStop: BusStop? = BusStop(),
    @SerializedName("ps") var busStopList: ArrayList<BusStop> = arrayListOf()
)
