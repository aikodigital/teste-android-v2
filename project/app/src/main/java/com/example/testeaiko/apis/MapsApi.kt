package com.example.testeaiko.apis

import com.example.testeaiko.datamodels.ModelRoute
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsApi {
    @GET("maps/api/directions/json")
    fun getRoute(
            @Query("origin") origin: String,
            @Query("destination") destination: String,
            @Query("key") key: String
    ): Call<ModelRoute>

}