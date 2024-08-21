package com.matreis.teste.sptrans.data.datasource.busstop

import com.matreis.teste.sptrans.data.api.SpTransService
import javax.inject.Inject

class BusStopRemoteDataSourceImp @Inject constructor(
    private val spTransService: SpTransService
): BusStopRemoteDataSource {
}