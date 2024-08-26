package com.matreis.teste.sptrans.data.repository.arrivaltime

import com.matreis.teste.sptrans.data.datasource.arrivaltime.ArrivalTimeRemoteDataSource
import com.matreis.teste.sptrans.domain.model.TimeWithBusStop
import retrofit2.Response
import javax.inject.Inject

class ArrivalTimeRepositoryImp @Inject constructor(
    private val remoteDataSource: ArrivalTimeRemoteDataSource
): ArrivalTimeRepository {

    override suspend fun getArrivalTimeByStopAndLine(
        stopCode: Long,
        lineCode: Long
    ): Response<TimeWithBusStop> = remoteDataSource.getArrivalTimeByStopAndLine(stopCode, lineCode)

    override suspend fun getArrivalTimeByLine(lineCode: Long): Response<TimeWithBusStop> = remoteDataSource.getArrivalTimeByLine(lineCode)

    override suspend fun getArrivalTimeByBusStop(stopCode: Long): Response<TimeWithBusStop> = remoteDataSource.getArrivalTimeByBusStop(stopCode)
}