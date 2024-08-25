package com.example.aikospbus.feature_bus_corridor.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApiService
import com.example.aikospbus.feature_bus_corridor.data.remote.dto.BusCorridorDto
import retrofit2.HttpException

class BusCorridorDataServiceImpl(private val apiService: SPTransApiService) : BusCorridorDataService {

    override suspend fun requestBusCorridorData(cookie: String): List<BusCorridorDto>? {
        return try {
            val response = apiService.getCorredores(cookie)
            println("Resposta completa: ${response}")
            response
        } catch (e: HttpException) {
            println("Erro na resposta da API: ${e.message()}")
            null
        } catch (e: Exception) {
            println("Erro ao fazer requisição: ${e.message}")
            null
        }
    }
}