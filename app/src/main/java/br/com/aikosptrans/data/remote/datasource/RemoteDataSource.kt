package br.com.aikosptrans.data.remote.datasource

import br.com.aikosptrans.data.remote.model.response.ArrivalForecastResponse
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
    suspend fun getBusStopByLine(idLine: String): List<BusStopResponse>
    suspend fun getArrivalForecastTime(idLine: String, idStop: String): ArrivalForecastResponse
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
    override suspend fun getBusStopByLine(idLine: String): List<BusStopResponse> = service.getBusStopByLine(idLine)
    override suspend fun getArrivalForecastTime(
        idLine: String,
        idStop: String
    ): ArrivalForecastResponse {
        return service.getArrivalForecastTime(
            idStop = idStop,
            idLine = idLine
        )
    }
}