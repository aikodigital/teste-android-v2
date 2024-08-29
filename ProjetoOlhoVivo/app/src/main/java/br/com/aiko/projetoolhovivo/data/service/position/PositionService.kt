package br.com.aiko.projetoolhovivo.data.service.position

import br.com.aiko.projetoolhovivo.data.model.position.PositionByListLine
import br.com.aiko.projetoolhovivo.data.model.position.PositionByVehicle
import retrofit2.http.GET
import retrofit2.http.Query

interface PositionService {
    @GET("Posicao")
    suspend fun getPositionByListLines(@Query("token") token: String): PositionByListLine

    @GET("Posicao/Linha")
    suspend fun getVehiclesPositionByCodeLine(
        @Query("token") token: String,
        @Query("codigoLinha") codeLine: Int
    ): PositionByVehicle
}