package com.cesarsoftdevelopment.aikopublictransport.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bus_line")
data class BusLineEntity(
    @PrimaryKey
    val lineCode: Int,
    val circularLine: Boolean,
    val firstLineNumber: String,
    val secondLineNumber: Int,
    val lineDirection: Int,
    val primaryTerminal: String,
    val secondaryTerminal: String
)
