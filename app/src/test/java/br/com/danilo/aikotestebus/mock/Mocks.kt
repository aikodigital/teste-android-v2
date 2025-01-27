package br.com.danilo.aikotestebus.mock

import br.com.danilo.aikotestebus.data.model.ArrivalForecastBusResponse
import br.com.danilo.aikotestebus.data.model.ArrivalForecastRelationResponse
import br.com.danilo.aikotestebus.data.model.ArrivalForecastResponse
import br.com.danilo.aikotestebus.data.model.ArrivalForecastStopResponse
import br.com.danilo.aikotestebus.data.model.BusResponse
import br.com.danilo.aikotestebus.data.model.BusesPositionResponse
import br.com.danilo.aikotestebus.data.model.BusesRelationResponse

val mockBusesPositionResponse = BusesPositionResponse(
    hourGenerated = "12:00",
    busList = listOf(
        BusesRelationResponse(
            fullNumber = "101",
            lineId = 1,
            address = 101,
            destination = "Destination 1",
            origin = "Origin 1",
            busQuantity = 5,
            buses = listOf(
                BusResponse(
                    prefixNumber = 101,
                    isAccessible = true,
                    dateTime = "2025-01-25T12:00:00",
                    latitude = -23.5505,
                    longitude = -46.6333
                )
            )
        ),
        BusesRelationResponse(
            fullNumber = "102",
            lineId = 2,
            address = 102,
            destination = "Destination 2",
            origin = "Origin 2",
            busQuantity = 3,
            buses = listOf(
                BusResponse(
                    prefixNumber = 102,
                    isAccessible = false,
                    dateTime = "2025-01-25T13:00:00",
                    latitude = -23.5510,
                    longitude = -46.6340
                )
            )
        )
    )
)

val mockArrivalForecastResponse = ArrivalForecastResponse(
    dateTime = "2025-01-25T12:00:00",
    busStop = ArrivalForecastStopResponse(
        idStop = 1,
        stopName = "Stop 1",
        latitude = -23.5505,
        longitude = -46.6333,
        busList = listOf(
            ArrivalForecastRelationResponse(
                letterComplete = "101",
                idLine = 1,
                flow = 1,
                lineDestination = "asd",
                lineOrigin = "sadksadp",
                buses = listOf(
                    ArrivalForecastBusResponse(
                        prefixNumber = 101,
                        isAccessible = true,
                        dateTime = "2025-01-25T12:00:00",
                        latitude = -23.5505,
                        longitude = -46.6333,
                        arrivalForecastTime = "12:30:00"
                    )
                )
            ),
            ArrivalForecastRelationResponse(
                letterComplete = "102",
                idLine = 2,
                flow = 2,
                lineDestination = "asd",
                lineOrigin = "sadksadp",
                buses = listOf(
                    ArrivalForecastBusResponse(
                        prefixNumber = 102,
                        isAccessible = false,
                        dateTime = "2025-01-25T13:00:00",
                        latitude = -23.5510,
                        longitude = -46.6340,
                        arrivalForecastTime = "13:30:00"
                    )
                )
            )
        )
    )
)