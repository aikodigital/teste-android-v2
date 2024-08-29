package com.example.viewtab.network.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GsonDateDeserializer: JsonDeserializer<Date> {

    private val DATE_FORMAT = "dd/MM/yyyy"

    private val DATETIME_FORMAT = "dd/MM/yyyy HH:mm"

    private val TIME_FORMAT = "HH:mm"

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        return try {
            parseDate(json.asString)
        } catch (ex: ParseException) {
            null
        }
    }

    @Throws(ParseException::class)
    private fun parseDate(dateString: String?): Date? {
        return if (dateString != null && !dateString.isEmpty()) {
            try {
                tryParseDatetime(dateString)
            } catch (ex: ParseException) {
                try {
                    tryParseDate(dateString)
                } catch (pe: ParseException) {
                    tryParseTime(dateString)
                }
            }
        } else {
            null
        }
    }

    @Throws(ParseException::class)
    private fun tryParseDatetime(datetimeString: String): Date? {
        val formatter: DateFormat = SimpleDateFormat(DATETIME_FORMAT, Locale.getDefault())
        return formatter.parse(datetimeString)
    }

    @Throws(ParseException::class)
    private fun tryParseDate(dateString: String): Date? {
        val formatter: DateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return formatter.parse(dateString)
    }

    @Throws(ParseException::class)
    private fun tryParseTime(timeString: String): Date? {
        val formatter: DateFormat = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        return formatter.parse(timeString)
    }
}