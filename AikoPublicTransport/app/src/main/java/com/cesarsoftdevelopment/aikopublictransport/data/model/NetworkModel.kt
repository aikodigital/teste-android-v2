package com.cesarsoftdevelopment.aikopublictransport.data.model

import com.google.gson.annotations.SerializedName

data class BusLineItem(
    @SerializedName("c")
    val fullSign : Int,
    @SerializedName("cl")
    val lineCode : Int,
    @SerializedName("lc")
    val circularLine : Boolean,
    @SerializedName("lt")
    val firstLineNumber : String?,
    @SerializedName("tl")
    val secondLineNumber : Int,
    @SerializedName("sl")
    val lineDirection : Int,
    @SerializedName("lt0")
    val lineDestinationSign : String?,
    @SerializedName("lt1")
    val lineOriginSign : String?,
    @SerializedName("qv")
    val quantityLocatedVehicles : Int,
    @SerializedName("tp")
    val primaryTerminal : String?,
    @SerializedName("ts")
    val secondaryTerminal : String?,
    @SerializedName("vs")
    val vehicles : List<VehicleItem>,
)


data class StopItem(
    @SerializedName("cp")
    val stopCode : Int,
    @SerializedName("np")
    val stopName : String?,
    @SerializedName("ed")
    val stopAddress : String?,
    @SerializedName("py")
    val stopLatitude : Double,
    @SerializedName("px")
    val stopLongitude : Double,
    @SerializedName("l")
    val lines : List<BusLineItem>,

    )

data class VehiclePosition(
    @SerializedName("hr")
    val currentTime : String,
    @SerializedName("vs")
    val vehicles : List<VehicleItem>
)


data class VehicleItem(
    @SerializedName("p")
    val vehiclePrefix : String?,
    @SerializedName("a")
    val disabledAccess : Boolean,
    @SerializedName("t")
    val estimatedArrivalTime : String?,
    @SerializedName("ta")
    val universalTime : String?,
    @SerializedName("py")
    val vehicleLatitude : Double,
    @SerializedName("px")
    val vehicleLongitude : Double,
)

data class EstimatedArrivalTime (
    @SerializedName("hr")
    val currentTime : String,
    @SerializedName("p")
    val stop : StopItem,
)
