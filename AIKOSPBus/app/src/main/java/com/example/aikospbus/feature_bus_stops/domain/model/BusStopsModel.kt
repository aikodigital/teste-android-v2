package com.example.aikospbus.feature_bus_stops.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busStops")
class BusStopsModel (
    @PrimaryKey(autoGenerate = false)
    val codigoParada: Int,
    val nomeParada: String,
    val enderecoParada: String,
    val latitude: Double,
    val longitude: Double
)