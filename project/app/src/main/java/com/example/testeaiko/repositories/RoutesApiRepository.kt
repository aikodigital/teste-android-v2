package com.example.testeaiko.repositories

import com.example.testeaiko.BuildConfig
import com.example.testeaiko.datamodels.ModelRoute
import com.example.testeaiko.services.RoutesApiService
import retrofit2.Response

class RoutesApiRepository(private val apiService: RoutesApiService) : ApiRepository() {
    fun getRoutes(origin: String, destination: String, callback: (Result<Response<ModelRoute>>) -> Unit) {
        val call = apiService.createApi().getRoute(origin, destination, BuildConfig.MAPS_API_KEY)
        makeApiCall(call, callback)
    }
}