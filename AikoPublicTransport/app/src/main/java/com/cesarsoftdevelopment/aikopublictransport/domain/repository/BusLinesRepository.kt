package com.cesarsoftdevelopment.aikopublictransport.domain.repository

interface BusLineRepository {
    suspend fun getBusLine(lineCode : Int)

}