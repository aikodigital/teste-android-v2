package br.com.aiko.projetoolhovivo.data.service.auth

import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("Login/Autenticar")
    suspend fun authorize(@Query("token") token: String): Boolean
}