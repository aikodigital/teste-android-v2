package com.matreis.teste.sptrans.data.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpTransService {

    @GET("Login/Autenticar")
    suspend fun auth(@Query("token") token: String): Response<String>

    @GET("Linha/Buscar")
    suspend fun getLines(@Query("termosBusca") searchTerm: String): Response<String>

    @GET("Linha/BuscarLinhaSentido")
    suspend fun getLinesByDirection(@Query("termosBusca") searchTerm: String, @Query("sentido") direction: String): Response<String>

    @GET("Parada/Buscar")
    suspend fun getBusStop(@Query("termosBusca") searchTerm: String): Response<String>

    @GET("Parada/BuscarParadasPorLinha")
    suspend fun getBusStopByLine(@Query("codigoLinha") lineCode: Long): Response<String>

    @GET("Posicao/Linha")
    suspend fun getVehiclePositionByLine(@Query("codigoLinha") lineCode: Long): Response<String>

    @GET("Previsao")
    suspend fun getArrivalTimeByStopAndLine(@Query("codigoParada") stopCode: Long, @Query("codigoLinha") lineCode: Long): Response<String>

    @GET("Previsao/Linha")
    suspend fun getArrivalTimeByLine(@Query("codigoLinha") lineCode: Long): Response<String>

}