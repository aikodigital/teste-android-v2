package com.example.aikospbus.feature_bus_corridor.data.repository

import com.example.aikospbus.feature_bus_corridor.data.data_source.BusCorridorDataSource
import com.example.aikospbus.feature_bus_corridor.data.remote.api.BusCorridorDataService
import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel
import com.example.aikospbus.util.Resource
import io.ktor.client.features.ClientRequestException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusCorridorRepositoryImpl @Inject constructor(
    private val localDataSource: BusCorridorDataSource,
    private val apiData: BusCorridorDataService
) : BusCorridorRepository {

    override suspend fun insertBusCorridor(busCorridorModel: List<BusCorridorModel>) {
        localDataSource.insertBusCorridor(busCorridorModel)
    }

    override suspend fun getBusCorridor() = localDataSource.getBusCorridor()

    override fun getRemoteBusCorridor(cookie: String): Flow<Resource<List<BusCorridorModel>?>> =
        flow {
            emit(Resource.Loading())
            val localBusCorridorData = localDataSource.getBusCorridor()
            emit(Resource.Loading(data = localBusCorridorData))

            try {
                val busCorridorData = apiData.requestBusCorridorData(cookie)

                if (busCorridorData == null) {
                    emit(
                        Resource.Error(
                            message = "oops, something went wrong",
                            data = localBusCorridorData
                        )
                    )
                } else {
                    val updateBusCorridorModelData: List<BusCorridorModel> =
                        busCorridorData.map { dto ->
                            BusCorridorModel(
                                codigoCorredor = dto.codigoCorredor,
                                nomeCorredor = dto.nomeCorredor
                            )
                        }


                    localDataSource.insertBusCorridor(updateBusCorridorModelData)
                    val newCorridorData = localDataSource.getBusCorridor()
                    emit(Resource.Success(data = newCorridorData))
                }
            } catch (e: ClientRequestException) {
                emit(
                    Resource.Error(
                        message = "opps, something went wrong",
                        data = localBusCorridorData
                    )
                )
            }
        }
}