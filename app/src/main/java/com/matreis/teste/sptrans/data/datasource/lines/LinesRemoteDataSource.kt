package com.matreis.teste.sptrans.data.datasource.lines

import com.matreis.teste.sptrans.domain.model.Line
import retrofit2.Response

interface LinesRemoteDataSource {

    suspend fun getLines(searchTerm: String): Response<List<Line>>
    suspend fun getLinesByDirection(searchTerm: String, direction: Int): Response<List<Line>>

}