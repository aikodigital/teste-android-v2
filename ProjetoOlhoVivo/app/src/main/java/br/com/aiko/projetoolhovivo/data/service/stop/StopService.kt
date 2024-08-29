package br.com.aiko.projetoolhovivo.data.service.stop

import br.com.aiko.projetoolhovivo.data.model.stop.Stop
import retrofit2.http.GET
import retrofit2.http.Query

interface StopService {
    @GET("Parada/Buscar")
    suspend fun getStops(@Query("token") token: String, @Query("termosBusca") search: String): List<Stop>

    @GET("Parada/BuscarParadasPorLinha")
    suspend fun getStopsByCodeLine(@Query("token") token: String, @Query("codigoLinha") codeLine: Int): List<Stop>
}