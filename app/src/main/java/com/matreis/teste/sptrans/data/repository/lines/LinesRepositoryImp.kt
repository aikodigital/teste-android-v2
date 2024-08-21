package com.matreis.teste.sptrans.data.repository.lines

import com.matreis.teste.sptrans.data.datasource.lines.LinesRemoteDataSource
import javax.inject.Inject

class LinesRepositoryImp @Inject constructor(
    private val remoteDataSource: LinesRemoteDataSource
): LinesRepository {

}