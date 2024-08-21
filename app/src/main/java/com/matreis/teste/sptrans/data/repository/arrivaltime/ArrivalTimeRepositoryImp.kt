package com.matreis.teste.sptrans.data.repository.arrivaltime

import com.matreis.teste.sptrans.data.datasource.arrivaltime.ArrivalTimeRemoteDataSource
import javax.inject.Inject

class ArrivalTimeRepositoryImp @Inject constructor(
    private val remoteDataSource: ArrivalTimeRemoteDataSource
): ArrivalTimeRepository {
}