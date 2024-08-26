package com.example.aikospbus.roomDataBase.roomConverters

import androidx.room.TypeConverter
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.BusLinesTestDto
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.ParadaTestDto
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.VeiculosTestDto
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.BusLinesTestMode
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.ParadaTestModel
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.VeiculoTestModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StopPredictionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromStopPredictionModel(stopPredictionModel: StopPredictionModel): String {
        return gson.toJson(stopPredictionModel)
    }

    @TypeConverter
    fun toStopPredictionModel(data: String?): StopPredictionModel? {
        if (data == null) return null
        val type = object : TypeToken<StopPredictionModel>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromParadaTestDto(parada: ParadaTestDto): String {
        return gson.toJson(parada)
    }

    @TypeConverter
    fun toParadaTestDto(paradaString: String): ParadaTestDto {
        return gson.fromJson(paradaString, ParadaTestDto::class.java)
    }

    @TypeConverter
    fun fromBusLinesTestDto(linhas: List<BusLinesTestDto>): String {
        return gson.toJson(linhas)
    }

    @TypeConverter
    fun toBusLinesTestDto(linhasString: String): List<BusLinesTestDto> {
        val type = object : TypeToken<List<BusLinesTestDto>>() {}.type
        return gson.fromJson(linhasString, type)
    }

    @TypeConverter
    fun fromVeiculosTestDto(veiculos: List<VeiculosTestDto>): String {
        return gson.toJson(veiculos)
    }

    @TypeConverter
    fun toVeiculosTestDto(veiculosString: String): List<VeiculosTestDto> {
        val type = object : TypeToken<List<VeiculosTestDto>>() {}.type
        return gson.fromJson(veiculosString, type)
    }

    // Conversores para modelos de domínio
    @TypeConverter
    fun fromParadaTestModel(parada: ParadaTestModel): String {
        return gson.toJson(parada)
    }

    @TypeConverter
    fun toParadaTestModel(paradaString: String): ParadaTestModel {
        return gson.fromJson(paradaString, ParadaTestModel::class.java)
    }

    @TypeConverter
    fun fromBusLinesTestMode(linhas: List<BusLinesTestMode>): String {
        return gson.toJson(linhas)
    }

    @TypeConverter
    fun toBusLinesTestMode(linhasString: String): List<BusLinesTestMode> {
        val type = object : TypeToken<List<BusLinesTestMode>>() {}.type
        return gson.fromJson(linhasString, type)
    }

    @TypeConverter
    fun fromVeiculoTestModel(veiculos: List<VeiculoTestModel>): String {
        return gson.toJson(veiculos)
    }

    @TypeConverter
    fun toVeiculoTestModel(veiculosString: String): List<VeiculoTestModel> {
        val type = object : TypeToken<List<VeiculoTestModel>>() {}.type
        return gson.fromJson(veiculosString, type)
    }


}