package com.cesarsoftdevelopment.aikopublictransport.data.repository

import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.UserRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.UserRepository
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource

class UserRepositoryImpl(private val userRemoteDataSource: UserRemoteDataSource) : UserRepository {
    override suspend fun authenticate(token: String): Resource<Boolean> {

        val response = userRemoteDataSource.authenticate(token)

        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


}