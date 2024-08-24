package com.example.aikospbus.feature_bus_location.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busLocation")
data class BusLocation(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String = ""
)
