package com.example.aikospbus.roomDataBase.roomConverters

import androidx.room.TypeConverter
import com.example.aikospbus.feature_bus_lines.data.remote.dto.BusLinesDto
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BusLinesConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromBusLinesModel(busLinesModel: BusLinesModel?): String {
        return gson.toJson(busLinesModel)
    }

    @TypeConverter
    fun toBusLinesModel(data: String?): BusLinesModel? {
        if (data == null) return null
        val type = object : TypeToken<BusLinesModel>() {}.type
        return gson.fromJson(data, type)
    }

}