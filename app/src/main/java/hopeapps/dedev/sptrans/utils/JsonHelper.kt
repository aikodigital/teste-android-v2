package hopeapps.dedev.sptrans.utils

import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.models.MapPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


object JsonHelper {
    fun BusLine.encodeJson(): String =
        URLEncoder.encode(Json.encodeToString(this), StandardCharsets.UTF_8.toString())

    fun BusStop.encodeJson(): String =
        URLEncoder.encode(Json.encodeToString(this), StandardCharsets.UTF_8.toString())

    fun MapPoint.encodeJson(): String =
        URLEncoder.encode(Json.encodeToString(this), StandardCharsets.UTF_8.toString())

    fun String.decodeBusLineJson(): BusLine =
        Json.decodeFromString(URLDecoder.decode(this, "UTF-8"))

    fun String.decodeBusStopJson(): BusStop =
        Json.decodeFromString(URLDecoder.decode(this, "UTF-8"))

    fun String.decodeMapPoint(): MapPoint = Json.decodeFromString(URLDecoder.decode(this, "UTF-8"))
}