package com.matreis.teste.sptrans.data.repository.lines

import com.matreis.teste.sptrans.domain.model.Line
import retrofit2.Response

interface LinesRepository {

    suspend fun getLines(searchTerm: String): Response<List<Line>>
    suspend fun getLinesByDirection(searchTerm: String, direction: Int): Response<List<Line>>

}