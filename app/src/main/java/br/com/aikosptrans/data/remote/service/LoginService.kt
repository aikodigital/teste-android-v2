package br.com.aikosptrans.data.remote.service

import retrofit2.http.POST

interface LoginService {

    @POST("Login/Autenticar")
    suspend fun authenticate(): Boolean
}