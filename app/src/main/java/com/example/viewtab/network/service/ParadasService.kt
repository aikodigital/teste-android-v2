package com.example.viewtab.network.service

import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.model.Parada
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ParadasService {

    @GET("Parada/Buscar")
    fun getBuscar( @Query("termosBusca") termosBusca: String): Single<Response<List<Parada?>?>?>?


    @GET("Parada/BuscarParadasPorLinha")
    fun getBuscarLinha(
        @Query("codigoLinha") codeLinha: Long,
    ): Single<Response<List<Parada?>?>?>?

    @GET("Parada/BuscarParadasPorLinha")
    fun getBuscarCorredor(
        @Query("codigoCorredor") codeCorredor: Int,
    ): Single<Response<List<Parada?>?>?>?
}