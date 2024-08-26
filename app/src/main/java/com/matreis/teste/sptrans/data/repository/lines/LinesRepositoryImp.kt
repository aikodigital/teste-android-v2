package com.matreis.teste.sptrans.data.repository.lines

import androidx.lifecycle.LiveData
import com.matreis.teste.sptrans.data.datasource.lines.LinesLocalDataSource
import com.matreis.teste.sptrans.data.datasource.lines.LinesRemoteDataSource
import com.matreis.teste.sptrans.domain.model.Line
import retrofit2.Response
import javax.inject.Inject

class LinesRepositoryImp @Inject constructor(
    private val remoteDataSource: LinesRemoteDataSource,
    private val localDataSource: LinesLocalDataSource
): LinesRepository {
    override suspend fun getLines(searchTerm: String): Response<List<Line>> = remoteDataSource.getLines(searchTerm)

    override suspend fun getLinesByDirection(
        searchTerm: String,
        direction: Int
    ): Response<List<Line>> = remoteDataSource.getLinesByDirection(searchTerm, direction)

    override suspend fun insertFavoriteLine(line: Line) = localDataSource.insertLine(line)

    override fun getAllFavoriteLines(): LiveData<List<Line>> = localDataSource.getAllLines()

    override suspend fun deleteAllFavoriteLines() = localDataSource.deleteAllLines()

    override suspend fun deleteFavoriteLineByCode(lineCode: Long) = localDataSource.deleteLineByCode(lineCode)

    override suspend fun getFavoriteLineByCode(lineCode: Long): LiveData<Line> = localDataSource.getLineByCode(lineCode)

}