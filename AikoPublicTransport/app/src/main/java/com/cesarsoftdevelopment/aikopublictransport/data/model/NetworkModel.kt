package com.cesarsoftdevelopment.aikopublictransport.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

import kotlinx.parcelize.Parcelize
@Parcelize
data class BusLineItem(
    @SerializedName("c")
    val fullSign : String?,
    @SerializedName("cl")
    val lineCode : Int?,
    @SerializedName("lc")
    val circularLine : Boolean?,
    @SerializedName("lt")
    val firstLineNumber : String?,
    @SerializedName("tl")
    val secondLineNumber : Int?,
    @SerializedName("sl")
    val lineDirection : Int?,
    @SerializedName("lt0")
    val lineDestinationSign : String?,
    @SerializedName("lt1")
    val lineOriginSign : String?,
    @SerializedName("qv")
    val quantityLocatedVehicles : Int?,
    @SerializedName("tp")
    val primaryTerminal : String?,
    @SerializedName("ts")
    val secondaryTerminal : String?,
    @SerializedName("vs")
    val vehicles : List<VehicleItem?>? = emptyList(),
) : Parcelable


@Parcelize
data class StopItem(
    @SerializedName("cp")
    val stopCode : Long?,
    @SerializedName("np")
    val stopName : String?,
    @SerializedName("ed")
    val stopAddress : String?,
    @SerializedName("py")
    val stopLatitude : Double?,
    @SerializedName("px")
    val stopLongitude : Double?,
    @SerializedName("l")
    val lines : List<BusLineItem>? = emptyList(),
    ) : Parcelable

@Parcelize
data class VehiclePosition(
    @SerializedName("hr")
    val currentTime : String?,
    @SerializedName("vs")
    val vehicles : List<VehicleItem?>? = emptyList()
) : Parcelable


@Parcelize
data class VehicleItem(
    @SerializedName("p")
    val vehiclePrefix : String?,
    @SerializedName("a")
    val disabledAccess : Boolean?,
    @SerializedName("t")
    val estimatedArrivalTime : String?,
    @SerializedName("ta")
    val universalTime : String?,
    @SerializedName("py")
    val vehicleLatitude : Double?,
    @SerializedName("px")
    val vehicleLongitude : Double?,
) : Parcelable

@Parcelize
data class EstimatedArrivalTime (
    @SerializedName("hr")
    val currentTime : String?,
    @SerializedName("p")
    val stop : StopItem?,
) : Parcelable


@Parcelize
data class Objects(
    val stops : List<StopItem?>? = emptyList(),
    val vehicle : VehiclePosition?
) : Parcelable
