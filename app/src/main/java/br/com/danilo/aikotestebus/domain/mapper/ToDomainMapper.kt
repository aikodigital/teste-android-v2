package br.com.danilo.aikotestebus.domain.mapper

import br.com.danilo.aikotestebus.data.model.ArrivalForecastBusResponse
import br.com.danilo.aikotestebus.data.model.ArrivalForecastRelationResponse
import br.com.danilo.aikotestebus.data.model.ArrivalForecastResponse
import br.com.danilo.aikotestebus.data.model.ArrivalForecastStopResponse
import br.com.danilo.aikotestebus.data.model.BusResponse
import br.com.danilo.aikotestebus.data.model.BusStopLineResponse
import br.com.danilo.aikotestebus.data.model.BusesPositionResponse
import br.com.danilo.aikotestebus.data.model.BusesRelationResponse
import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.data.model.StopDetailResponse
import br.com.danilo.aikotestebus.domain.model.ArrivalForecast
import br.com.danilo.aikotestebus.domain.model.ArrivalForecastBus
import br.com.danilo.aikotestebus.domain.model.ArrivalForecastRelation
import br.com.danilo.aikotestebus.domain.model.ArrivalForecastStop
import br.com.danilo.aikotestebus.domain.model.Bus
import br.com.danilo.aikotestebus.domain.model.BusesPosition
import br.com.danilo.aikotestebus.domain.model.BusesRelation
import br.com.danilo.aikotestebus.domain.model.LineDetail
import br.com.danilo.aikotestebus.domain.model.StopDetail
import br.com.danilo.aikotestebus.domain.model.entity.MapMarker
import com.google.android.gms.maps.model.LatLng

fun List<LineDetailResponse?>?.toLineDetailList(): List<LineDetail> {
    return this?.map { lineDetailResponse ->
        lineDetailResponse?.toLineDetail() ?: LineDetail(
            lineId = 0,
            isCircular = false,
            firstLabelNumber = "",
            secondLabelNumber = 0,
            sense = 0,
            mainTerminal = "",
            secondaryTerminal = ""
        )
    } ?: emptyList()
}

fun LineDetailResponse?.toLineDetail() : LineDetail {
    return LineDetail(
        lineId = this?.lineId.handleOptional(),
        isCircular = this?.isCircular.handleOptional(),
        firstLabelNumber = this?.firstLabelNumber.handleOptional(),
        secondLabelNumber = this?.secondLabelNumber.handleOptional(),
        sense = this?.sense.handleOptional(),
        mainTerminal = this?.mainTerminal.handleOptional(),
        secondaryTerminal = this?.secondaryTerminal.handleOptional()
    )
}

fun BusesPositionResponse?.toBusesPosition(): BusesPosition {
    return BusesPosition(
        hourGenerated = this?.hourGenerated.handleOptional(),
        busList = this?.busList?.toBusesRelationList() ?: emptyList()
    )
}

fun List<BusesRelationResponse?>?.toBusesRelationList(): List<BusesRelation> {
    return this?.map { busesRelationResponse ->
        busesRelationResponse?.toBusesRelation() ?: BusesRelation(
            fullNumber = "",
            lineId = 0,
            address = 0,
            destination = "",
            origin = "",
            busQuantity = 0,
            buses = emptyList()
        )
    } ?: emptyList()
}

fun BusesRelationResponse?.toBusesRelation(): BusesRelation {
    return BusesRelation(
        fullNumber = this?.fullNumber.handleOptional(),
        lineId = this?.lineId.handleOptional(),
        address = this?.address.handleOptional(),
        destination = this?.destination.handleOptional(),
        origin = this?.origin.handleOptional(),
        busQuantity = this?.busQuantity.handleOptional(),
        buses = this?.buses?.toBusList() ?: emptyList()
    )
}

fun List<BusResponse?>?.toBusList(): List<Bus> {
    return this?.map { busResponse ->
        busResponse?.toBus() ?: Bus(
            prefixNumber = 0,
            isAccessible = false,
            dateTime = "",
            latitude = 0.0,
            longitude = 0.0
        )
    } ?: emptyList()
}

fun BusResponse?.toBus(): Bus {
    return Bus(
        prefixNumber = this?.prefixNumber.handleOptional(),
        isAccessible = this?.isAccessible.handleOptional(),
        dateTime = this?.dateTime.handleOptional(),
        latitude = this?.latitude.handleOptional(),
        longitude = this?.longitude.handleOptional()
    )
}

fun StopDetailResponse?.toStopDetail(): StopDetail {
    return StopDetail(
        stopId = this?.stopId.handleOptional(),
        name = this?.name.handleOptional(),
        address = this?.address.handleOptional(),
        latitude = this?.latitude.handleOptional(),
        longitude = this?.longitude.handleOptional()
    )
}

fun ArrivalForecastResponse?.toArrivalForecast(): ArrivalForecast {
    return ArrivalForecast(
        dateTime = this?.dateTime.handleOptional(),
        busStop = this?.busStop?.toArrivalForecastStop()
    )
}

fun ArrivalForecastStopResponse?.toArrivalForecastStop(): ArrivalForecastStop {
    return ArrivalForecastStop(
        idStop = this?.idStop.handleOptional(),
        stopName = this?.stopName.handleOptional(),
        latitude = this?.latitude.handleOptional(),
        longitude = this?.longitude.handleOptional(),
        busList = this?.busList?.toArrivalForecastRelationList() ?: emptyList()
    )
}

fun List<ArrivalForecastRelationResponse?>?.toArrivalForecastRelationList(): List<ArrivalForecastRelation> {
    return this?.map { arrivalForecastRelationResponse ->
        arrivalForecastRelationResponse?.toArrivalForecastRelation() ?: ArrivalForecastRelation(
            number = "",
            idLine = 0,
            flow = 0,
            buses = emptyList()
        )
    } ?: emptyList()
}

fun ArrivalForecastRelationResponse?.toArrivalForecastRelation(): ArrivalForecastRelation {
    return ArrivalForecastRelation(
        number = this?.number.handleOptional(),
        idLine = this?.idLine.handleOptional(),
        flow = this?.flow.handleOptional(),
        buses = this?.buses?.toArrivalForecastBusList() ?: emptyList()
    )
}

fun List<ArrivalForecastBusResponse?>?.toArrivalForecastBusList(): List<ArrivalForecastBus> {
    return this?.map { arrivalForecastBusResponse ->
        arrivalForecastBusResponse?.toArrivalForecastBus() ?: ArrivalForecastBus(
            prefixNumber = 0,
            isAccessible = false,
            dateTime = "",
            latitude = 0.0,
            longitude = 0.0,
            arrivalForecastTime = ""
        )
    } ?: emptyList()
}

fun ArrivalForecastBusResponse?.toArrivalForecastBus(): ArrivalForecastBus {
    return ArrivalForecastBus(
        prefixNumber = this?.prefixNumber.handleOptional(),
        isAccessible = this?.isAccessible.handleOptional(),
        dateTime = this?.dateTime.handleOptional(),
        latitude = this?.latitude.handleOptional(),
        longitude = this?.longitude.handleOptional(),
        arrivalForecastTime = this?.arrivalForecastTime.handleOptional()
    )
}

fun List<BusStopLineResponse?>?.toBusStopDetailList(): List<StopDetail> {
    return this?.map { busStopLineResponse ->
        busStopLineResponse?.toStopDetail() ?: StopDetail(
            stopId = 0,
            name = "",
            address = "",
            latitude = 0.0,
            longitude = 0.0
        )
    } ?: emptyList()
}

fun BusStopLineResponse?.toStopDetail(): StopDetail {
    return StopDetail(
        stopId = this?.stopId.handleOptional(),
        name = this?.name.handleOptional(),
        address = this?.address.handleOptional(),
        latitude = this?.latitude.handleOptional(),
        longitude = this?.longitude.handleOptional()
    )
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

fun String?.handleOptional() = this ?: ""
fun Int?.handleOptional() = this ?: 0
fun Boolean?.handleOptional() = this ?: false
fun Double?.handleOptional() = this ?: 0.0