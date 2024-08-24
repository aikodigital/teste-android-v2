package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.UserRemoteDataSource
import retrofit2.Response

class UserRemoteDataSourceImpl : UserRemoteDataSource {
    override suspend fun authenticate(token: String): Response<Boolean> {
        TODO("Not yet implemented")
    }
}