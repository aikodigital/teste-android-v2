package com.example.viewtab.network.service

import com.example.viewtab.network.model.Linha
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AutenticarService {

    @POST("Login/Autenticar")
    fun AutenticarValidity(): Single<Response<Boolean?>>

}