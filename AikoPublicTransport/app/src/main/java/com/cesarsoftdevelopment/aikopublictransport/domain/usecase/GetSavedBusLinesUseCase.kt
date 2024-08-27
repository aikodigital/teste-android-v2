package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.TransportInfoRepository
import kotlinx.coroutines.flow.Flow

class GetSavedBusLinesUseCase(private val transportInfoRepository: TransportInfoRepository) {
    fun invoke(): Flow<List<BusLineEntity>> {
        return transportInfoRepository.getSavedBusLines()
    }
}