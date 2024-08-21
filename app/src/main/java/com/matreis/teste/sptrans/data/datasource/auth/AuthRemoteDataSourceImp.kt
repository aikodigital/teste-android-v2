package com.matreis.teste.sptrans.data.datasource.auth

import com.matreis.teste.sptrans.data.api.SpTransService
import javax.inject.Inject

class AuthRemoteDataSourceImp @Inject constructor(
    private val spTransService: SpTransService
): AuthRemoteDataSource {
}