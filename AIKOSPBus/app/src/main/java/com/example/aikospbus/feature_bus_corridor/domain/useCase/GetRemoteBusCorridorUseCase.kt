package com.example.aikospbus.feature_bus_corridor.domain.useCase

import com.example.aikospbus.feature_bus_corridor.data.repository.BusCorridorRepository
import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteBusCorridorUseCase @Inject constructor(private val busCorridorRepository: BusCorridorRepository){

    operator fun invoke(cookie: String): Flow<Resource<List<BusCorridorModel>?>> {
        return busCorridorRepository.getRemoteBusCorridor(cookie)
    }
}