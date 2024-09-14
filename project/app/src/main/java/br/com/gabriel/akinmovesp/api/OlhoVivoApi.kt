package br.com.gabriel.akinmovesp.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface OlhoVivoApi {

    @POST("Login/Autenticar")
    suspend fun authenticate (@Query("token") token: String): Response<Boolean>

}