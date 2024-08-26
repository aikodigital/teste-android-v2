package com.example.aikospbus.roomDataBase.roomConverters

import androidx.room.TypeConverter
import com.example.aikospbus.feature_bus_location.data.remote.dto.VehicleDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BusLocationConverter {

    @TypeConverter
    fun fromVeiculoList(vehicleDtos: List<VehicleDto>): String {
        val gson = Gson()
        return gson.toJson(vehicleDtos)
    }

    @TypeConverter
    fun toVeiculoList(veiculosString: String): List<VehicleDto> {
        val gson = Gson()
        val type = object : TypeToken<List<VehicleDto>>() {}.type
        return gson.fromJson(veiculosString, type)
    }
}