package com.matreis.teste.sptrans.domain.usecase

import com.matreis.teste.sptrans.data.repository.busstop.BusStopRepository
import javax.inject.Inject

class GetBusStopUseCase @Inject constructor(
    private val busStopRepository: BusStopRepository
) {
    suspend operator fun invoke(term: String) = busStopRepository.getBusStopByTerm(term)
    suspend operator fun invoke(lineCode: Long) = busStopRepository.getBusStopByLine(lineCode)
}