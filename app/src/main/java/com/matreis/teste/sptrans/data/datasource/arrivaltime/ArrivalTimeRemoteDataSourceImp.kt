package com.matreis.teste.sptrans.data.datasource.arrivaltime

import com.matreis.teste.sptrans.data.api.SpTransService
import javax.inject.Inject

class ArrivalTimeRemoteDataSourceImp @Inject constructor(
    private val service: SpTransService
): ArrivalTimeRemoteDataSource {

}