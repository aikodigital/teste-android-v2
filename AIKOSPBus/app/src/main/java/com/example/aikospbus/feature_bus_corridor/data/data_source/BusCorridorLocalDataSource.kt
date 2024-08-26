package com.example.aikospbus.feature_bus_corridor.data.data_source

import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel

class BusCorridorLocalDataSource(private val busCorridorDao: BusCorridorDao) :
    BusCorridorDataSource {

    override suspend fun insertBusCorridor(busCorridorModel: List<BusCorridorModel>) {
        return busCorridorDao.insertBusCorridor(busCorridorModel)
    }

    override suspend fun getBusCorridor(): List<BusCorridorModel>? {
        return busCorridorDao.getBusCorridor()
    }

}