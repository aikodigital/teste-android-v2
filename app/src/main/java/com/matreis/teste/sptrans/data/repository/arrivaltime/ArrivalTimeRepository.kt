package com.matreis.teste.sptrans.data.repository.arrivaltime

import com.matreis.teste.sptrans.domain.model.TimeWithBusStop
import retrofit2.Response

interface ArrivalTimeRepository {
    suspend fun getArrivalTimeByStopAndLine(stopCode: Long, lineCode: Long): Response<TimeWithBusStop>
    suspend fun getArrivalTimeByLine(lineCode: Long): Response<TimeWithBusStop>
    suspend fun getArrivalTimeByBusStop(stopCode: Long): Response<TimeWithBusStop>

}