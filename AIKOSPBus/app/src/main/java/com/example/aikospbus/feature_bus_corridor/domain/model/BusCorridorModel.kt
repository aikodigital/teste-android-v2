package com.example.aikospbus.feature_bus_corridor.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busCorridor")
class BusCorridorModel (
    @PrimaryKey(autoGenerate = false)
    val codigoCorredor: Int,
    val nomeCorredor: String
)
