package com.matreis.teste.sptrans.data.datasource.busstop

import com.matreis.teste.sptrans.data.api.SpTransService
import com.matreis.teste.sptrans.domain.model.BusStop
import retrofit2.Response
import javax.inject.Inject

class BusStopRemoteDataSourceImp @Inject constructor(
    private val spTransService: SpTransService
): BusStopRemoteDataSource {
    override suspend fun getBusStopByTerm(term: String): Response<List<BusStop>> = spTransService.getBusStop(term)
    override suspend fun getBusStopByLine(lineCode: Long): Response<List<BusStop>> = spTransService.getBusStopByLine(lineCode)
}