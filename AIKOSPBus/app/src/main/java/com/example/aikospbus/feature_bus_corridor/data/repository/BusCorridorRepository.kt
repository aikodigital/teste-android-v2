package com.example.aikospbus.feature_bus_corridor.data.repository

import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow

interface BusCorridorRepository {

    suspend fun insertBusCorridor(busCorridorModel: List<BusCorridorModel>)

    suspend fun getBusCorridor(): List<BusCorridorModel>?

    fun getRemoteBusCorridor(cookie: String): Flow<Resource<List<BusCorridorModel>?>>

}