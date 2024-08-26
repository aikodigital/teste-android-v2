package com.matreis.teste.sptrans.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Line(
    @SerializedName("cl") @PrimaryKey var codLine: Long? = null,
    @SerializedName("lc") var circleMode: Boolean? = null,
    @SerializedName("lt") var firstNumericSign: String? = null,
    @SerializedName("sl") var direction: Int? = null,
    @SerializedName("tl") var secondNumericSign: Int? = null,
    @SerializedName("tp") var mainTerminalDescription: String? = null,
    @SerializedName("ts") var secondaryTerminalDescription: String? = null,
    var fullSign: String? = null
): Serializable
