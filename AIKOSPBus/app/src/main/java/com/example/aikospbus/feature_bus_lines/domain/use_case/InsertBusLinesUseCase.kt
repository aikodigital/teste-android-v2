package com.example.aikospbus.feature_bus_lines.domain.use_case

import com.example.aikospbus.feature_bus_lines.data.repository.BusLinesRepository
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import javax.inject.Inject

class InsertBusLinesUseCase @Inject constructor(private val busLinesRepository: BusLinesRepository) {

    suspend operator fun invoke(busLinesModel: List<BusLinesModel>) {
        busLinesRepository.insertBusLines(busLinesModel)
    }
}