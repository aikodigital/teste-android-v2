package com.cesarsoftdevelopment.aikopublictransport.data.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.BusLinesRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.BusLinesRepository
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
import retrofit2.Response

class BusLinesRepositoryImpl(
    private val busLinesRemoteDataSource: BusLinesRemoteDataSource
) : BusLinesRepository {
    override suspend fun getBusLines(termsSearch: String): Resource<List<BusLineItem>> {

        val response = busLinesRemoteDataSource.getBusLines(termsSearch)

        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())

    }


}