package com.matreis.teste.sptrans.data.api

import com.matreis.teste.sptrans.BuildConfig
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Route
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            try {
                val authResponse = authenticate()
                val cookie = authResponse.headers().toMultimap()["Set-Cookie"]?.first() ?: ""
                response.request.newBuilder()
                    .header("Cookie", cookie)
                    .build()
            }catch (e: Exception){
                null
            }
        }
    }

    private suspend fun authenticate(): retrofit2.Response<String> {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofitBuilder.create(SpTransService::class.java)
        return service.auth(BuildConfig.API_TOKEN)
    }

}