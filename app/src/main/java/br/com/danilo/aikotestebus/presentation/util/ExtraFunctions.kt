package br.com.danilo.aikotestebus.presentation.util

import br.com.danilo.aikotestebus.domain.model.ArrivalForecast
import br.com.danilo.aikotestebus.domain.model.BusesPosition
import br.com.danilo.aikotestebus.domain.model.LineDetail
import br.com.danilo.aikotestebus.domain.model.StopDetail
import br.com.danilo.aikotestebus.domain.model.entity.MapMarker
import com.google.android.gms.maps.model.LatLng
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

fun mapBusesToMapMarkers(busesPosition: BusesPosition): List<MapMarker> {
    return busesPosition.busList.flatMap { busesRelation ->
        busesRelation.buses?.map { bus ->
            MapMarker(
                titleText = busesRelation.fullNumber,
                snippetText = "Origem: ${busesRelation.origin}, Destino: ${busesRelation.destination}",
                location = LatLng(bus.latitude, bus.longitude)
            )
        } ?: emptyList()
    }
}

fun createMapMarkerFromPrefix(
    arrivalForecast: ArrivalForecast,
    prefix: Int,
    locateResult: (LatLng) -> Unit
): List<MapMarker> {
    arrivalForecast.busStop.busList.forEach { relation ->
        val bus = relation.buses.firstOrNull { it.prefixNumber == prefix }
        bus?.let {
            val locate = LatLng(it.latitude, it.longitude)
            locateResult(locate)
            return listOf(
                MapMarker(
                    titleText = "Ônibus: ${it.prefixNumber}, Linha: ${relation.letterComplete}",
                    snippetText = "Tempo estimado: ${it.arrivalForecastTime}",
                    location = LatLng(it.latitude, it.longitude)
                )
            )
        }
    }
    return emptyList()
}