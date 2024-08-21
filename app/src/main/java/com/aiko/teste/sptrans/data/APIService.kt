package com.aiko.teste.sptrans.data

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    @POST("Login/Autenticar")
    fun authenticate(@Query("token") token: String) : Call<Boolean>
}