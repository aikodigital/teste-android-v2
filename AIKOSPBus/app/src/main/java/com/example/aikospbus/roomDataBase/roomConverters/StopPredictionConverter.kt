package com.example.aikospbus.roomDataBase.roomConverters

import androidx.room.TypeConverter
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.ParadaTestDto
import com.google.gson.Gson

class StopPredictionConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromParadaTestDto(parada: ParadaTestDto): String {
        return gson.toJson(parada)
    }

    @TypeConverter
    fun toParadaTestDto(paradaString: String): ParadaTestDto {
        return gson.fromJson(paradaString, ParadaTestDto::class.java)
    }

}