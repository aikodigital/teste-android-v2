package com.matreis.teste.sptrans.data.repository.busstop

import com.matreis.teste.sptrans.data.datasource.busstop.BusStopRemoteDataSource
import javax.inject.Inject

class BusStopRepositoryImp @Inject constructor(
    private val remoteDataSource: BusStopRemoteDataSource
): BusStopRepository {

}