package com.example.aikospbus.feature_bus_stop_prediction.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.ParadaTestDto

@Entity(tableName = "stopPrediction")
class StopPredictionModel(
    @PrimaryKey(autoGenerate = false)
    val horaPrevisao: String,
    val parada: ParadaTestDto
)

class VeiculoTestModel(
    val latitude: Double,
    val longitude: Double
)
