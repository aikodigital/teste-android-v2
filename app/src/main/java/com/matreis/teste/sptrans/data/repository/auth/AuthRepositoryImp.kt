package com.matreis.teste.sptrans.data.repository.auth

import com.matreis.teste.sptrans.data.datasource.auth.AuthRemoteDataSource
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
): AuthRepository {
}