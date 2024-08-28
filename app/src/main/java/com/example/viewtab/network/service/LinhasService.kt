package com.example.viewtab.network.service

import com.example.viewtab.network.model.Linha
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LinhasService {

    @GET("Linha/Buscar")
    fun getBuscar( @Query("termosBusca") termo: String): Single<Response<List<Linha?>?>?>?


    @GET("Linha/BuscarLinhaSentido")
    fun getBuscarLinhaSentido(
        @Query("termosBusca") termo: String,
        @Query("sentido") sentido: Int
    ): Single<Response<List<Linha?>?>?>?
}