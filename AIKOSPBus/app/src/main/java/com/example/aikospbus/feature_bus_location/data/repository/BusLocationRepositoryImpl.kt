package com.example.aikospbus.feature_bus_location.data.repository

import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDataSource
import com.example.aikospbus.feature_bus_location.data.remote.api.BusLocationDataService
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.util.Resource
import io.ktor.client.features.ClientRequestException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BusLocationRepositoryImpl @Inject constructor(
    private val localDataSource: BusLocationDataSource,
    private val apiData: BusLocationDataService
) : BusLocationRepository {

    override suspend fun insertBusLocation(busLocationModel: BusLocationModel) {
        localDataSource.insertBusLocation(busLocationModel)
    }

    override suspend fun getBusLocation() = localDataSource.getBusLocation()

    override fun getRemoteBusLocation(
        cookie: String,
        lineCode: Int
    ): Flow<Resource<BusLocationModel>> = flow {
        emit(Resource.Loading())
        val localBusLocationData = localDataSource.getBusLocation()
        emit(Resource.Loading(data = localBusLocationData))

        try {
            val busLocationData = apiData.requestBusLocationData(cookie, lineCode)
            println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA $busLocationData")

            if (busLocationData == null) {
                emit(
                    Resource.Error(
                        message = "oops, something went wrong",
                        data = localBusLocationData
                    )
                )
            } else {
                if (busLocationData.vehicleDtos.isEmpty()) {
                    emit(
                        Resource.Error(
                            message = "oops, something went wrong",
                            data = localBusLocationData
                        )
                    )
                } else {
                    val updateBusLocationModelData = BusLocationModel(
                        horaConsulta = busLocationData.horaConsulta,
                        vehicleDtos = busLocationData.vehicleDtos
                    )

                    localDataSource.insertBusLocation(updateBusLocationModelData)
                    val newBusLocationData = localDataSource.getBusLocation()
                    emit(Resource.Success(data = newBusLocationData))
                }
            }
        } catch (e: ClientRequestException) {
            emit(
                Resource.Error(
                    message = "oops, something went wrong",
                    data = localBusLocationData
                )
            )
        }
    }


    override suspend fun updateBusLocation(busLocationModel: BusLocationModel) {
        localDataSource.updateBusLocation(busLocationModel)
    }


}