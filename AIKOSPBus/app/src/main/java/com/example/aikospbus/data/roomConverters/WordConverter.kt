package com.example.aikospbus.data.roomConverters

import androidx.room.TypeConverter
import com.example.aikospbus.feature_bus_location.domain.model.BusLocation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections
import java.util.Date

class WordConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromWordsToString(busLocationList: List<BusLocation>): String {
        return gson.toJson(busLocationList)
    }

    @TypeConverter
    fun stringToWords(wordString: String): List<BusLocation>? {
        if (wordString.isEmpty()) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<BusLocation>>() {}.type

        return gson.fromJson(wordString,listType)
    }

    @TypeConverter
    fun fromTimestamp(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}