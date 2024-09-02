package com.example.testeaiko.services

import com.example.testeaiko.apis.TransitApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TransitApiService(private val okHttpClient: OkHttpClient) {
    private val baseUrl = "https://api.olhovivo.sptrans.com.br/v2.1/"
    private var retrofit: TransitApi? = null
    fun createApi(): TransitApi {
        if (retrofit != null) return retrofit as TransitApi
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TransitApi::class.java)
        return retrofit as TransitApi
    }
}