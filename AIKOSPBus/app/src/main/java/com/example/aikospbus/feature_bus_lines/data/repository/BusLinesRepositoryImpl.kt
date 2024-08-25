package com.example.aikospbus.feature_bus_lines.data.repository

import com.example.aikospbus.feature_bus_lines.data.data_source.BusLinesDataSource
import com.example.aikospbus.feature_bus_lines.data.remote.api.BusLinesDataService
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import com.example.aikospbus.util.Resource
import io.ktor.client.features.ClientRequestException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusLinesRepositoryImpl @Inject constructor(
    private val localDataSource: BusLinesDataSource,
    private val apiData: BusLinesDataService
): BusLinesRepository {

    override suspend fun insertBusLines(busLinesModel: List<BusLinesModel>) {
        localDataSource.insertBusLines(busLinesModel)
    }

    override suspend fun getBusLines() = localDataSource.getBusLines()

    override fun getRemoteBusLines(
        cookie: String,
        searchTerms: String
    ): Flow<Resource<List<BusLinesModel>?>> = flow {
        emit(Resource.Loading())
        val localBusLinesData = localDataSource.getBusLines()
        emit(Resource.Loading(data = localBusLinesData))

        try {
            val busLinesData = apiData.requestBusLinesData(cookie,searchTerms)

            if (busLinesData == null) {
                emit(
                    Resource.Error(
                        message = "oops, something went wrong",
                        data = localBusLinesData
                    )
                )
            } else {
                if (busLinesData != null) {
                    val updateBusLinesModelData: List<BusLinesModel> = busLinesData.map { dto ->
                        BusLinesModel(
                            codigoLinha = dto.codigoLinha,
                            circular = dto.circular,
                            letreiro = dto.letreiro,
                            sentido = dto.sentido,
                            terminalPrincipal = dto.terminalPrincipal,
                            tipo = dto.tipo,
                            terminalSecundario = dto.terminalSecundario
                        )
                    }

                    localDataSource.insertBusLines(updateBusLinesModelData)
                    val newBusLinesData = localDataSource.getBusLines()
                    emit(Resource.Success(data = newBusLinesData))
                }
            }
        } catch (e: ClientRequestException) {
            emit(
                Resource.Error(
                    message = "opps, something went wrong",
                    data = localBusLinesData
                )
            )
        }
    }
}