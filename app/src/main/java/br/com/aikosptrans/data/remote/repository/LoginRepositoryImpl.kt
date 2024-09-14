package br.com.aikosptrans.data.remote.repository

import br.com.aikosptrans.data.remote.datasource.LoginDataSource
import br.com.aikosptrans.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val dataSource: LoginDataSource
) : LoginRepository {
    override suspend fun authenticate(): Boolean = dataSource.authenticate()
}