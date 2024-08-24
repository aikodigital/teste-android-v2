package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource

import retrofit2.Response
interface AuthenticateDataSource {
    suspend fun authenticate(token: String): Response<Boolean>

}