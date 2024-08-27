package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.TransportInfoRepository

class SaveBusLinesUseCase(private val transportInfoRepository: TransportInfoRepository) {

    suspend fun invoke(busLine: BusLineEntity) = transportInfoRepository.saveBusLine(busLine)

}