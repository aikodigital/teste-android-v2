package com.matreis.teste.sptrans.data.datasource.lines

import com.matreis.teste.sptrans.data.api.SpTransService
import javax.inject.Inject

class LinesRemoteDataSourceImp @Inject constructor(
    private val service: SpTransService
): LinesRemoteDataSource {
}