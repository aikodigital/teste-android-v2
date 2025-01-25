package br.com.danilo.aikotestebus.presentation.util

import br.com.danilo.aikotestebus.domain.model.LineDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun encodeLineDetailItem(item: LineDetail): String {
    val gson = Gson()
    return gson.toJson(item)
}

fun decodeLineDetailItem(encodedString: String?): LineDetail {
    val gson = Gson()
    val itemType = object : TypeToken<LineDetail>() {}.type
    return gson.fromJson(encodedString, itemType)
}