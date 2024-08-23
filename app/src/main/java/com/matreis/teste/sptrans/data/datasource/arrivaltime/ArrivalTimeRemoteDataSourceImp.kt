package com.matreis.teste.sptrans.data.datasource.arrivaltime

import com.matreis.teste.sptrans.data.api.SpTransService
import com.matreis.teste.sptrans.domain.model.TimeWithBusStop
import retrofit2.Response
import javax.inject.Inject

class ArrivalTimeRemoteDataSourceImp @Inject constructor(
    private val service: SpTransService
): ArrivalTimeRemoteDataSource {

    override suspend fun getArrivalTimeByStopAndLine(
        stopCode: Long,
        lineCode: Long
    ): Response<TimeWithBusStop> = service.getArrivalTimeByStopAndLine(stopCode, lineCode)

    override suspend fun getArrivalTimeByLine(lineCode: Long): Response<TimeWithBusStop> = service.getArrivalTimeByLine(lineCode)

    override suspend fun getArrivalTimeByBusStop(stopCode: Long): Response<TimeWithBusStop> = service.getArrivalTimeByBusStop(stopCode)

}