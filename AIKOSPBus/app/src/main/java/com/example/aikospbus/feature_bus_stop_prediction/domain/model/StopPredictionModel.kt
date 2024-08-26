package com.example.aikospbus.feature_bus_stop_prediction.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.BusLinesTestDto
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.ParadaTestDto
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.VeiculosTestDto

@Entity(tableName = "stopPrediction")
class StopPredictionModel (
    @PrimaryKey(autoGenerate = false)
    val horaPrevisao: String,
    val parada: ParadaTestDto
)

class ParadaTestModel(
    val codigoParada: Int,
    val nomeParada: String,
    val latitudeParada: Double,
    val longitudeParada: Double,
    val linhas: List<BusLinesTestDto>
)

class BusLinesTestMode(
    val codigoLinha: String,
    val codigoLinhaCompleto: Int,
    val sentidoLinha: Int,
    val origem: String,
    val destino: String,
    val quantidadeVeiculos: Int,
    val veiculos: List<VeiculosTestDto>
)

class VeiculoTestModel(
    val prefixo: String,
    val horarioChegada: String,
    val acessibilidade: Boolean,
    val horarioAtualizacao: String,
    val latitude: Double,
    val longitude: Double
)
