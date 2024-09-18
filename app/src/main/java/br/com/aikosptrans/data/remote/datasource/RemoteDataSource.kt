package br.com.aikosptrans.data.remote.datasource

import br.com.aikosptrans.data.remote.model.response.BusLineDetailResponse
import br.com.aikosptrans.data.remote.model.response.BusStopResponse
import br.com.aikosptrans.data.remote.model.response.BusesLocationResponse
import br.com.aikosptrans.data.remote.service.ApiService
import br.com.aikosptrans.util.service
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface RemoteDataSource {
    suspend fun authenticate(): Boolean
    suspend fun getBusesLocation(): BusesLocationResponse
    suspend fun getBusStop(query: String): List<BusStopResponse>
    suspend fun getBusLineDetail(query: String): List<BusLineDetailResponse>
}

class RemoteDataSourceImpl(
    retrofit: Retrofit.Builder,
    client: OkHttpClient.Builder
): RemoteDataSource {
    private val service by service<ApiService>(
        retrofit = retrofit,
        client = client
    )

    override suspend fun authenticate(): Boolean = service.authenticate()
    override suspend fun getBusesLocation(): BusesLocationResponse = service.getBusesLocation()
    override suspend fun getBusStop(query: String): List<BusStopResponse> = service.getBusStop(query)
    override suspend fun getBusLineDetail(query: String): List<BusLineDetailResponse> = service.getBusLine(query)
}