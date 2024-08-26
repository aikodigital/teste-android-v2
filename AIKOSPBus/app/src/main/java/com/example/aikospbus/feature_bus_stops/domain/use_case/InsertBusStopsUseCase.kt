package com.example.aikospbus.feature_bus_stops.domain.use_case

import com.example.aikospbus.feature_bus_stops.data.repository.BusStopsRepository
import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel
import javax.inject.Inject

class InsertBusStopsUseCase @Inject constructor(private val busStopsRepository: BusStopsRepository) {

    suspend operator fun invoke(busStopsModel: List<BusStopsModel>) {
        busStopsRepository.insertBusStops(busStopsModel)
    }
}