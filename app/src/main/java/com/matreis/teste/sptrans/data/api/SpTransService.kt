package com.matreis.teste.sptrans.data.api

import android.app.Service
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.Line
import com.matreis.teste.sptrans.domain.model.TimeWithBusStop
import com.matreis.teste.sptrans.domain.model.TimeWithVehicle
import com.matreis.teste.sptrans.domain.model.VehiclePosition
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface SpTransService {

    @POST("Login/Autenticar")
    suspend fun auth(@Query("token") token: String): Response<String>

    @GET("Linha/Buscar")
    suspend fun getLines(@Query("termosBusca") searchTerm: String): Response<List<Line>>

    @GET("Linha/BuscarLinhaSentido")
    suspend fun getLinesByDirection(@Query("termosBusca") searchTerm: String, @Query("sentido") direction: Int): Response<List<Line>>

    @GET("Parada/Buscar")
    suspend fun getBusStop(@Query("termosBusca") searchTerm: String): Response<List<BusStop>>

    @GET("Parada/BuscarParadasPorLinha")
    suspend fun getBusStopByLine(@Query("codigoLinha") lineCode: Long): Response<List<BusStop>>

    @GET("Posicao")
    suspend fun getAllVehiclePosition(): Response<VehiclePosition>

    @GET("Posicao/Linha")
    suspend fun getVehiclePositionByLine(@Query("codigoLinha") lineCode: Long): Response<TimeWithVehicle>

    @GET("Previsao")
    suspend fun getArrivalTimeByStopAndLine(@Query("codigoParada") stopCode: Long, @Query("codigoLinha") lineCode: Long): Response<TimeWithBusStop>

    @GET("Previsao/Linha")
    suspend fun getArrivalTimeByLine(@Query("codigoLinha") lineCode: Long): Response<TimeWithBusStop>

    @GET("Previsao/Parada")
    suspend fun getArrivalTimeByBusStop(@Query("codigoParada") stopCode: Long): Response<TimeWithBusStop>

}