package com.matreis.teste.sptrans.domain.usecase

import com.matreis.teste.sptrans.data.repository.arrivaltime.ArrivalTimeRepository
import javax.inject.Inject

class GetArrivalTimeUseCase @Inject constructor(
    private val repository: ArrivalTimeRepository
) {
    suspend operator fun invoke(stopCode: Long, lineCode: Long) = repository.getArrivalTimeByStopAndLine(stopCode, lineCode)
    suspend fun getByLine(lineCode: Long) = repository.getArrivalTimeByLine(lineCode)
    suspend fun getByBusStop(stopCode: Long) = repository.getArrivalTimeByBusStop(stopCode)
}