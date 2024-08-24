package com.cesarsoftdevelopment.aikopublictransport.data.remote.network

import com.cesarsoftdevelopment.aikopublictransport.data.remote.model.NetworkBusLine
import com.cesarsoftdevelopment.aikopublictransport.data.remote.model.NetworkEstimatedArrivalTime
import com.cesarsoftdevelopment.aikopublictransport.data.remote.model.NetworkStop
import com.cesarsoftdevelopment.aikopublictransport.data.remote.model.NetworkVehiclePosition
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PublicTransportApi {
    @GET("/Linha/Buscar")
    suspend fun getLines(
        @Query("termosBusca") termsSearch: String
    ): Response<NetworkBusLine>

    @GET("/Parada/Buscar")
    suspend fun getStopsByLine(
        @Query("codigoLinha") lineCode: Int
    ): Response<NetworkStop>

    @GET("/Parada/Buscar")
    suspend fun getVehiclePositionByLine(
        @Query("codigoLinha") lineCode: Int
    ): Response<NetworkVehiclePosition>

    @GET("/Previsao/Parada")
    suspend fun getEstimatedArrivalTimeByStop(
        @Query("codigoParada") stopCode: Int
    ): Response<NetworkEstimatedArrivalTime>

    @GET("/Previsao/Parada")
    suspend fun authenticate(
        @Query("token") token: String
    ): Response<Boolean>

}