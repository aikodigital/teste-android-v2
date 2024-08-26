package com.example.aikospbus.feature_bus_stops.data.repository

import com.example.aikospbus.feature_bus_stops.data.data_source.BusStopsDataSource
import com.example.aikospbus.feature_bus_stops.data.remote.api.BusStopsDataService
import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel
import com.example.aikospbus.util.Resource
import io.ktor.client.features.ClientRequestException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusStopsRepositoryImpl @Inject constructor(
    private val localDataSource: BusStopsDataSource,
    private val apiData: BusStopsDataService
) : BusStopsRepository {

    override suspend fun insertBusStops(busStopsModel: List<BusStopsModel>) {
        localDataSource.insertBusStops(busStopsModel)
    }

    override suspend fun getBusStops() = localDataSource.getBusStops()

    override fun getRemoteBusStops(
        cookie: String,
        searchTerms: String
    ): Flow<Resource<List<BusStopsModel>?>> = flow {
        emit(Resource.Loading())
        val localBusStopsData = localDataSource.getBusStops()
        emit(Resource.Loading(data = localBusStopsData))

        try {
            val busStopsData = apiData.requestBusStopsData(cookie, searchTerms)

            if (busStopsData == null) {
                emit(
                    Resource.Error(
                        message = "oops, something went wrong",
                        data = localBusStopsData
                    )
                )
            } else {
                val updateBusStopsModelData: List<BusStopsModel> = busStopsData.map { dto ->
                    BusStopsModel(
                        codigoParada = dto.codigoParada,
                        nomeParada = dto.nomeParada,
                        enderecoParada = dto.enderecoParada,
                        latitude = dto.latitude,
                        longitude = dto.longitude
                    )
                }


                localDataSource.insertBusStops(updateBusStopsModelData)
                val newStopsData = localDataSource.getBusStops()
                emit(Resource.Success(data = newStopsData))
            }
        } catch (e: ClientRequestException) {
            emit(
                Resource.Error(
                    message = "opps, something went wrong",
                    data = localBusStopsData
                )
            )
        }
    }

}