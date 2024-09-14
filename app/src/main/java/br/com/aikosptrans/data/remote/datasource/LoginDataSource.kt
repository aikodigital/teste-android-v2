package br.com.aikosptrans.data.remote.datasource

import br.com.aikosptrans.data.remote.service.LoginService
import br.com.aikosptrans.util.service
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface LoginDataSource {
    suspend fun authenticate(): Boolean
}

class LoginDataSourceImpl(
    retrofit: Retrofit.Builder,
    client: OkHttpClient.Builder
): LoginDataSource {
    private val loginService by service<LoginService>(
        retrofit = retrofit,
        client = client
    )

    override suspend fun authenticate(): Boolean = loginService.authenticate()
}