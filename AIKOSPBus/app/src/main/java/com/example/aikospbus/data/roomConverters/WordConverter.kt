package com.example.aikospbus.data.roomConverters

import androidx.room.TypeConverter
import com.example.aikospbus.feature_api_sp_trans.remote.models.Veiculo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WordConverter {

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