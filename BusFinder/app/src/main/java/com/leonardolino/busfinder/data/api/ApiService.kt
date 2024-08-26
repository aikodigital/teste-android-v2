package com.leonardolino.busfinder.data.api

import com.leonardolino.busfinder.data.model.BusStopDto
import com.leonardolino.busfinder.data.model.EstimatedArrivalDto
import com.leonardolino.busfinder.data.model.LineInfoDto
import com.leonardolino.busfinder.data.model.VehiclePositionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/Login/Autenticar")
    suspend fun authenticate(@Query("token") token: String): Response<Boolean>

    @GET("/Parada/Buscar")
    suspend fun getBusStops(@Query("termosBusca") terms: String): List<BusStopDto>

    @GET("/Previsao/Parada")
    suspend fun getNextArrivals(@Query("codigoParada") stopCode: Int): EstimatedArrivalDto

    @GET("/Posicao/Linha")
    suspend fun getVehiclesPosition(@Query("codigoLinha") line: Int): VehiclePositionDto

    @GET("/Linha/BuscarLinhaSentido")
    suspend fun getLineInfo(
        @Query("termosBusca") terms: String,
        @Query("sentido") lineDirection: Int
    ): List<LineInfoDto>
}