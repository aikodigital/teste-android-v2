package com.example.testeaiko.services

import com.example.testeaiko.apis.MapsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RoutesApiService(private val okHttpClient: OkHttpClient) {
    private val baseUrl = "https://maps.googleapis.com/"
    private var retrofit: MapsApi? = null
    fun createApi(): MapsApi {
        if (retrofit != null) return retrofit as MapsApi
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MapsApi::class.java)
        return retrofit as MapsApi
    }

}