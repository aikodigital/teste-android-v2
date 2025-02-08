package hopeapps.dedev.sptrans.utils

import hopeapps.dedev.sptrans.data.model.BusLine
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object JsonHelper {
    fun BusLine.encodeJson(): String = Json.encodeToString(this)
    fun String.decodeJson(): BusLine = Json.decodeFromString(this)
}