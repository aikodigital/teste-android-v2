package hopeapps.dedev.sptrans.domain.mappers

import hopeapps.dedev.sptrans.data.models.BusLineDto
import hopeapps.dedev.sptrans.data.models.BusStopDto
import hopeapps.dedev.sptrans.data.models.BusStopPredictionDto
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop



fun List<BusStopDto>.toBusStopDomain(): List<BusStop> {
    return map { busStopDetail ->
        BusStop(
            busStopDetail.idCodeBusStop,
            busStopDetail.name,
            busStopDetail.address,
            busStopDetail.latitude,
            busStopDetail.longitude
        )
    }
}

fun List<BusLineDto>.toBusLineDomain(): List<BusLine> {
    return map { busLineDto ->
        BusLine(
            lineId = busLineDto.lineId,
            isCircular = busLineDto.isCircular,
            firstLabelNumber = busLineDto.firstLabelNumber,
            secondLabelNumber = busLineDto.secondLabelNumber,
            sense = busLineDto.sense,
            mainTerminal = busLineDto.mainTerminal,
            secondaryTerminal = busLineDto.secondaryTerminal,
        )
    }
}

fun BusStopPredictionDto.toBusStopPredictionDomain(): List<BusPrediction> {
    val busPredictionList = mutableListOf<BusPrediction>()
    busStop.listOfLinesFound.forEach {
        val destination = it.destination
        val origin = it.origin
        it.vehicleList.forEach { vehicle ->
            vehicle.px
            vehicle.py
            vehicle.hourLastLocation
            vehicle.arrivalForecast
            vehicle.accessibleForDisability
            busPredictionList.add(
                BusPrediction(
                    destination = destination,
                    origin = origin,
                    px = vehicle.px,
                    py = vehicle.py,
                    lastUpdateTime = vehicle.hourLastLocation,
                    predictionTime = vehicle.arrivalForecast,
                    accessibleVehicle = if (vehicle.accessibleForDisability) "sim" else "não"
                )
            )
        }
    }
    return busPredictionList
}
