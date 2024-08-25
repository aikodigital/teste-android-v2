package com.example.aikospbus.roomDataBase.roomConverters

import androidx.room.TypeConverter
import com.example.aikospbus.feature_bus_location.domain.model.Veiculo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BusLocationConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromVeiculoList(veiculos: List<Veiculo>): String {
        val gson = Gson()
        return gson.toJson(veiculos)
    }

    @TypeConverter
    fun toVeiculoList(veiculosString: String): List<Veiculo> {
        val gson = Gson()
        val type = object : TypeToken<List<Veiculo>>() {}.type
        return gson.fromJson(veiculosString, type)
    }
}