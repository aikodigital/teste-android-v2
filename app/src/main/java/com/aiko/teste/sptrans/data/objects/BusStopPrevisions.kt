package com.aiko.teste.sptrans.data.objects

import com.google.gson.annotations.SerializedName

data class BusStopPrevisions(
    @SerializedName("p") val busStop: BusStop?
) {
    override fun toString(): String {
        return "BusStopPrevisions(busStop=$busStop)"
    }
}