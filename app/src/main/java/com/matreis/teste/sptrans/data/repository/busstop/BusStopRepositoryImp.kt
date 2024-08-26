package com.matreis.teste.sptrans.data.repository.busstop

import com.matreis.teste.sptrans.data.datasource.busstop.BusStopRemoteDataSource
import com.matreis.teste.sptrans.domain.model.BusStop
import retrofit2.Response
import javax.inject.Inject

class BusStopRepositoryImp @Inject constructor(
    private val remoteDataSource: BusStopRemoteDataSource
): BusStopRepository {

    override suspend fun getBusStopByTerm(term: String): Response<List<BusStop>> = remoteDataSource.getBusStopByTerm(term)
    override suspend fun getBusStopByLine(lineCode: Long): Response<List<BusStop>> = remoteDataSource.getBusStopByLine(lineCode)

}