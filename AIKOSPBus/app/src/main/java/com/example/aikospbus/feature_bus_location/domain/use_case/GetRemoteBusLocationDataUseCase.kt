package com.example.aikospbus.feature_bus_location.domain.use_case

import com.example.aikospbus.feature_bus_location.data.repository.BusLocationRepository
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteBusLocationDataUseCase @Inject constructor(private val busLocationRepository: BusLocationRepository) {

    operator fun invoke(cookie: String, lineCode: Int): Flow<Resource<BusLocationModel>> {
        return busLocationRepository.getRemoteBusLocation(cookie, lineCode)
    }
}