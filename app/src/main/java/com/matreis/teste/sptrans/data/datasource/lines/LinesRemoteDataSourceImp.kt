package com.matreis.teste.sptrans.data.datasource.lines

import com.matreis.teste.sptrans.data.api.SpTransService
import com.matreis.teste.sptrans.domain.model.Line
import retrofit2.Response
import javax.inject.Inject

class LinesRemoteDataSourceImp @Inject constructor(
    private val service: SpTransService
): LinesRemoteDataSource {

    override suspend fun getLines(searchTerm: String): Response<List<Line>> = service.getLines(searchTerm)

    override suspend fun getLinesByDirection(
        searchTerm: String,
        direction: Int
    ): Response<List<Line>> = service.getLinesByDirection(searchTerm, direction)
}