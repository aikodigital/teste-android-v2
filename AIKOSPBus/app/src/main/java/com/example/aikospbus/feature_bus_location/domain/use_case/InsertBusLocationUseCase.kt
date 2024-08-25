package com.example.aikospbus.feature_bus_location.domain.use_case

import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.feature_bus_location.data.repository.BusLocationRepository
import javax.inject.Inject

class InsertBusLocationUseCase @Inject constructor(private val busLocationRepository: BusLocationRepository) {

    suspend operator fun invoke(busLocationModel: BusLocationModel) {
        busLocationRepository.insertBusLocation(busLocationModel)
    }
}