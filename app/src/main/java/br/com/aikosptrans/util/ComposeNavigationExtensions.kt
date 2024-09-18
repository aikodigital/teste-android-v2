package br.com.aikosptrans.util

import android.util.Base64
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> String?.decodeStringInKObject(): T? {
    return try {
        this?.let {
            val modelArg = Base64.decode(
                this,
                Base64.URL_SAFE
            )
            Json.decodeFromString<T>(String(modelArg))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

inline fun <reified T> T.encodeKObjectInString(): String {
    return try {
        Base64.encodeToString(
            Json.encodeToString(this)
                .toByteArray(),
            Base64.URL_SAFE
        )
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}