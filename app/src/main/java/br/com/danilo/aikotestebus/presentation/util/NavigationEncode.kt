package br.com.danilo.aikotestebus.presentation.util

import br.com.danilo.aikotestebus.domain.model.LineDetail
import br.com.danilo.aikotestebus.domain.model.StopDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun encodeLineDetailItem(item: LineDetail): String {
    val gson = Gson()
    return gson.toJson(item)
}

fun decodeLineDetailItem(encodedString: String?): LineDetail {
    val gson = Gson()
    val itemType = object : TypeToken<LineDetail>() {}.type
    return gson.fromJson(encodedString, itemType)
}

fun encodeStopDetailItem(item: StopDetail): String {
    val gson = Gson()
    val jsonString = gson.toJson(item)
    return URLEncoder.encode(jsonString, StandardCharsets.UTF_8.toString())
}

fun decodeStopDetailItem(encodedString: String?): StopDetail {
    val gson = Gson()
    val decodedString = URLDecoder.decode(encodedString, StandardCharsets.UTF_8.toString())
    val itemType = object : TypeToken<StopDetail>() {}.type
    return gson.fromJson(decodedString, itemType)
}