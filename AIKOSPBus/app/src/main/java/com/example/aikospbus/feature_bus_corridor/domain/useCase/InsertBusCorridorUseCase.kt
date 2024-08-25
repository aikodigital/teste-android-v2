package com.example.aikospbus.feature_bus_corridor.domain.useCase

import com.example.aikospbus.feature_bus_corridor.data.repository.BusCorridorRepository
import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel
import javax.inject.Inject

class InsertBusCorridorUseCase @Inject constructor(private val busCorridorRepository: BusCorridorRepository ) {

    suspend operator fun invoke(busCorridorModel: List<BusCorridorModel>) {
        busCorridorRepository.insertBusCorridor(busCorridorModel)
    }
}
