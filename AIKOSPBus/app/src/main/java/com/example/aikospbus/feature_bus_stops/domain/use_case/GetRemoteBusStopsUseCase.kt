package com.example.aikospbus.feature_bus_stops.domain.use_case

import com.example.aikospbus.feature_bus_stops.data.repository.BusStopsRepository
import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteBusStopsUseCase @Inject constructor(private val busStopsRepository: BusStopsRepository) {

    operator fun invoke(cookie: String, searchTerms: String) : Flow<Resource<List<BusStopsModel>?>> {
        return busStopsRepository.getRemoteBusStops(cookie,searchTerms)
    }
}