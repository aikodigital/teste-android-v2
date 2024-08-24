package com.leonardolino.busfinder.data.api

import com.leonardolino.busfinder.data.model.BusStopDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/Login/Autenticar")
    suspend fun authenticate(
        @Query("token") token: String): Response<Boolean>

    @GET("/Parada/Buscar")
    suspend fun getBusStops(@Query("termosBusca") terms: String): List<BusStopDto>
}