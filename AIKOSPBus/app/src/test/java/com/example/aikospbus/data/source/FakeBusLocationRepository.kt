package com.example.aikospbus.data.source

import com.example.aikospbus.feature_bus_location.data.repository.BusLocationRepository
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBusLocationRepository : BusLocationRepository {

    private var busLocationItem: MutableList<BusLocationModel> = mutableListOf()

    fun insertAllBusLocationData(busLocationModel: List<BusLocationModel>) {
        busLocationItem.addAll(busLocationModel)
    }

    override suspend fun insertBusLocation(busLocationModel: BusLocationModel) {
        busLocationItem.add(busLocationModel)
    }

    override suspend fun getBusLocation(): BusLocationModel {
        return busLocationItem[0]
    }

    override suspend fun updateBusLocation(busLocationModel: BusLocationModel) {
        busLocationItem[0] = busLocationModel
    }

    override fun getRemoteBusLocation(
        cookie: String,
        lineCode: Int
    ): Flow<Resource<BusLocationModel>> = flow {
        emit(Resource.Loading(data = busLocationItem[0]))
        emit(Resource.Error(message = "oops, something went wrong", data = busLocationItem[0]))
        emit(Resource.Success(busLocationItem[0]))
    }
}