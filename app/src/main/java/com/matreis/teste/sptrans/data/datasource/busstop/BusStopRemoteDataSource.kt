package com.matreis.teste.sptrans.data.datasource.busstop

import com.matreis.teste.sptrans.domain.model.BusStop
import retrofit2.Response

interface BusStopRemoteDataSource {
    suspend fun getBusStopByTerm(term: String): Response<List<BusStop>>
    suspend fun getBusStopByLine(lineCode: Long): Response<List<BusStop>>
}