package com.example.aikospbus.feature_bus_lines.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApiService
import com.example.aikospbus.feature_bus_lines.data.remote.dto.BusLinesDto
import retrofit2.HttpException

class BusLinesDataServiceImpl(private val apiService: SPTransApiService) : BusLinesDataService {

    override suspend fun requestBusLinesData(cookie: String, searchTerms: String): List<BusLinesDto>? {
        return try {
            val response = apiService.getLine(cookie, searchTerms)
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