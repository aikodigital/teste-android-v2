package br.com.danilo.aikotestebus.domain.model

data class LineDetail(
    val lineId: Int,
    val isCircular: Boolean,
    val firstLabelNumber: String,
    val secondLabelNumber: Int,
    val sense: Int,
    val mainTerminal: String,
    val secondaryTerminal: String
)

data class BusesPosition(
    val hourGenerated: String,
    val busList: List<BusesRelation>
)

data class BusesRelation(
    val fullNumber: String,
    val lineId: Int,
    val address: Int,
    val destination: String,
    val origin: String,
    val busQuantity: Int,
    val buses: List<Bus>?
)

data class Bus(
    val prefixNumber: Int,
    val isAccessible: Boolean,
    val dateTime: String,
    val latitude: Double,
    val longitude: Double
)

data class StopDetail(
    val stopId: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
)

data class ArrivalForecast(
    val dateTime: String,
    val busStop: ArrivalForecastStop
)

data class ArrivalForecastStop(
    val idStop: Int,
    val stopName: String,
    val latitude: Double,
    val longitude: Double,
    val busList: List<ArrivalForecastRelation>
)

data class ArrivalForecastRelation(
    val letterComplete: String,
    val idLine: Int,
    val lineDestination: String,
    val lineOrigin: String,
    val flow: Int,
    val buses: List<ArrivalForecastBus>
)

data class ArrivalForecastBus(
    val prefixNumber: Int,
    val isAccessible: Boolean,
    val dateTime: String,
    val latitude: Double,
    val longitude: Double,
    val arrivalForecastTime: String
)