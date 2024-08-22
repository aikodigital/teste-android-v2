package com.aiko.teste.sptrans.data

import com.aiko.teste.sptrans.data.objects.BusPositions
import com.aiko.teste.sptrans.data.objects.BusStop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    @POST("Login/Autenticar")
    fun authenticate(@Query("token") token: String) : Call<Boolean>

    @Headers("Content-Type: application/json")
    @GET("Posicao")
    fun getBusPositions() : Call<BusPositions>
    @Headers("Content-Type: application/json")
    @GET("Parada/Buscar")
    fun getBusStops(@Query("termosBusca") searchTerms: String) : Call<List<BusStop>>
}