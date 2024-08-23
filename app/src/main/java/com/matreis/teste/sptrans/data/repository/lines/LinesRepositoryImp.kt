package com.matreis.teste.sptrans.data.repository.lines

import com.matreis.teste.sptrans.data.datasource.lines.LinesRemoteDataSource
import com.matreis.teste.sptrans.domain.model.Line
import retrofit2.Response
import javax.inject.Inject

class LinesRepositoryImp @Inject constructor(
    private val remoteDataSource: LinesRemoteDataSource
): LinesRepository {
    override suspend fun getLines(searchTerm: String): Response<List<Line>> = remoteDataSource.getLines(searchTerm)

    override suspend fun getLinesByDirection(
        searchTerm: String,
        direction: Int
    ): Response<List<Line>> = remoteDataSource.getLinesByDirection(searchTerm, direction)

}