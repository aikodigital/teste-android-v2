package hopeapps.dedev.sptrans.domain.mappers

import hopeapps.dedev.sptrans.data.models.BusLineDto
import hopeapps.dedev.sptrans.data.models.BusStopDto
import hopeapps.dedev.sptrans.data.models.BusStopPredictionDto
import hopeapps.dedev.sptrans.data.models.LinesLocationsDto
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.utils.DateFormat.formattedDate

fun List<BusStopDto>.toBusStopDomain(): List<BusStop> = map { busStopDetail ->
    BusStop(
        idCodeBusStop = busStopDetail.idCodeBusStop,
        name = busStopDetail.name,
        address = busStopDetail.address,
        latitude = busStopDetail.latitude,
        longitude = busStopDetail.longitude
    )
}

fun List<BusLineDto>.toBusLineDomain(): List<BusLine> = map { busLineDto ->
    BusLine(
        lineId = busLineDto.lineId,
        isCircular = busLineDto.isCircular,
        firstLabelNumber = busLineDto.firstLabelNumber,
        secondLabelNumber = busLineDto.secondLabelNumber,
        sense = busLineDto.sense,
        mainTerminal = busLineDto.mainTerminal,
        secondaryTerminal = busLineDto.secondaryTerminal
    )
}

fun BusStopPredictionDto.toBusStopPredictionDomain(): List<BusPrediction> =
    busStop.listOfLinesFound.flatMap { line ->
        line.vehicleList.map { vehicle ->
            BusPrediction(
                destination = line.destination,
                origin = line.origin,
                px = vehicle.px,
                py = vehicle.py,
                lastUpdateTime = vehicle.hourLastLocation.formattedDate(),
                predictionTime = vehicle.arrivalForecast,
                accessibleVehicle = vehicle.accessibleForDisability,
                idLine = line.lineCode,
                firstToSecondTerminal = line.lineWay == 1
            )
        }
    }

fun LinesLocationsDto.toDynamicPointsDomain(): List<DynamicPoint> =
    vehiclesPerLine.flatMap { line ->
        line.vehicles.map { vehicle ->
            DynamicPoint(
                latitude = vehicle.latitude,
                longitude = vehicle.longitude,
                id = line.lineCode,
                lastUpdate = vehicle.lastUpdateTime.formattedDate(),
                accessible = vehicle.accessible,
                name = line.fullSign,
                prefix = vehicle.prefixVehicle
            )
        }
    }