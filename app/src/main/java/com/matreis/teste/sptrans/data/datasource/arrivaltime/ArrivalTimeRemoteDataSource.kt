package com.matreis.teste.sptrans.data.datasource.arrivaltime

import com.matreis.teste.sptrans.domain.model.TimeWithBusStop
import retrofit2.Response

interface ArrivalTimeRemoteDataSource {

    suspend fun getArrivalTimeByStopAndLine(stopCode: Long, lineCode: Long): Response<TimeWithBusStop>
    suspend fun getArrivalTimeByLine(lineCode: Long): Response<TimeWithBusStop>
    suspend fun getArrivalTimeByBusStop(stopCode: Long): Response<TimeWithBusStop>


}