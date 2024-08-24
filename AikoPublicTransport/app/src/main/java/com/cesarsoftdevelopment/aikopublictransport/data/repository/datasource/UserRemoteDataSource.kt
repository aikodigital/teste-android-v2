package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource

import retrofit2.Response
interface UserRemoteDataSource {
    suspend fun authenticate(token: String): Response<Boolean>

}