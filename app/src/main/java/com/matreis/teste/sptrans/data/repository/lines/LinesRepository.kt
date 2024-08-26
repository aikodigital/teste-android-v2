package com.matreis.teste.sptrans.data.repository.lines

import androidx.lifecycle.LiveData
import com.matreis.teste.sptrans.domain.model.Line
import retrofit2.Response

interface LinesRepository {
    suspend fun getLines(searchTerm: String): Response<List<Line>>
    suspend fun getLinesByDirection(searchTerm: String, direction: Int): Response<List<Line>>
    suspend fun insertFavoriteLine(line: Line)
    fun getAllFavoriteLines(): LiveData<List<Line>>
    suspend fun deleteAllFavoriteLines()
    suspend fun deleteFavoriteLineByCode(lineCode: Long)
    suspend fun getFavoriteLineByCode(lineCode: Long): LiveData<Line>
}