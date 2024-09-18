package br.com.aikosptrans.data.remote.mapper

import br.com.aikosptrans.data.remote.model.response.ArrivalForecastResponse
import br.com.aikosptrans.data.remote.model.response.BusLineDetailResponse
import br.com.aikosptrans.data.remote.model.response.BusLineResponse
import br.com.aikosptrans.data.remote.model.response.BusResponse
import br.com.aikosptrans.data.remote.model.response.BusStopResponse
import br.com.aikosptrans.domain.entity.Bus
import br.com.aikosptrans.domain.entity.BusDetail
import br.com.aikosptrans.domain.entity.BusLine
import br.com.aikosptrans.domain.entity.BusStop

fun List<BusLineResponse>.mapToBuses(): List<Bus>{
    val buses = this.map { busLine ->
        busLine.buses.map { bus ->
            Bus(
                busLine = busLine.mapToBusLine(),
                busDetail = bus.mapToBus()
            )
        }
    }
    return buses.flatten()
}

fun BusLineResponse.mapToBusLine(): BusLine {
    return BusLine(
        fullNumber = fullNumber,
        lineId = lineId,
        flow = flow,
        destination = destination,
        origin = origin
    )
}

fun BusResponse.mapToBus(): BusDetail {
    return BusDetail(
        prefix = prefix,
        isAccessible = isAccessible,
        dateTime = dateTime,
        latitude = latitude,
        longitude = longitude
    )
}

fun List<BusStopResponse>.mapToBusStop(): List<BusStop> {
    return this.map {
        BusStop(
            busStopId = it.busStopId,
            name = it.name,
            address = it.address,
            latitude = it.latitude,
            longitude = it.longitude
        )
    }
}

fun List<BusLineDetailResponse>.mapToBusLine(): List<BusLine> {
    return this.map {
        BusLine(
            fullNumber = "${it.firstNumber}-${it.secondNumber}",
            lineId = it.lineId,
            flow = it.flow,
            destination = if(it.flow == 1) it.secondaryTerminalName else it.mainTerminalName,
            origin = if(it.flow == 1) it.mainTerminalName else it.secondaryTerminalName,
            isCircular = it.isCircular
        )
    }
}

fun ArrivalForecastResponse.mapToArriveForecastTime(): List<String> {
    return this
        .busStop
        ?.busLines
        ?.flatMap { it.buses }
        ?.map { it.arrivalForecastTime } ?: listOf(this.dateTime)
}