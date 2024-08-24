package com.cesarsoftdevelopment.aikopublictransport.data.remote.model

import com.google.gson.annotations.SerializedName
class NetworkBusLine : ArrayList<NetworkBusLineItem>()
data class NetworkBusLineItem(
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
    val vehicles : List<NetworkVehicleItem>,
)

class NetworkStop : ArrayList<NetworkStopItem>()
data class NetworkStopItem(
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
    val lines : List<NetworkBusLineItem>,

)

data class NetworkVehiclePosition(
    @SerializedName("hr")
    val currentTime : String,
    @SerializedName("vs")
    val vehicles : List<NetworkVehicleItem>
)

data class NetworkVehicleItem(
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

data class NetworkEstimatedArrivalTime (
    @SerializedName("hr")
    val currentTime : String,
    @SerializedName("p")
    val stop : NetworkStopItem,
)
