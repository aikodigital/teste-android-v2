package com.cesarsoftdevelopment.aikopublictransport.data.network

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.data.model.EstimatedArrivalTime
import com.cesarsoftdevelopment.aikopublictransport.data.model.StopItem
import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PublicTransportApi {
    @GET("/Linha/Buscar")
    suspend fun getBusLines(
        @Query("termosBusca") termsSearch: String
    ): Response<List<BusLineItem>>

    @GET("/Parada/BuscarParadasPorLinha")
    suspend fun getStopsByLine(
        @Query("codigoLinha") lineCode: Int
    ): Response<List<StopItem>>

    @GET("/Posicao/Linha")
    suspend fun getVehiclesPositionByLine(
        @Query("codigoLinha") lineCode: Int
    ): Response<VehiclePosition>

    @GET("/Previsao/Parada")
    suspend fun getEstimatedArrivalTimesByStop(
        @Query("codigoParada") stopCode: Long?
    ): Response<EstimatedArrivalTime>

    @POST("/Login/Autenticar")
    suspend fun authenticate(
        @Query("token") token: String
    ): Response<Boolean>
}