package hopeapps.dedev.sptrans.utils

import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder


object JsonHelper {
    fun BusLine.encodeJson(): String = Json.encodeToString(this)
    fun BusStop.encodeJson(): String = Json.encodeToString(this)
    fun String.decodeBusLineJson(): BusLine = Json.decodeFromString(this)
    fun String.decodeBusStopJson(): BusStop = Json.decodeFromString(this)


    fun String.encodeToUrl(): String {
        return URLEncoder.encode(this, "UTF-8")
    }

    fun String.decodeFromUrl(): String {
        return URLDecoder.decode(this, "UTF-8")
    }
}