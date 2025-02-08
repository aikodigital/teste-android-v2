package hopeapps.dedev.sptrans.data.network

import hopeapps.dedev.sptrans.data.models.BusLineDto
import hopeapps.dedev.sptrans.data.models.BusStopDto
import hopeapps.dedev.sptrans.data.models.BusStopPredictionDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @POST("Login/Autenticar")
    suspend fun authApi() : Boolean

    @GET("Linha/Buscar")
    suspend fun getBusLinesWithNameOrNumber(@Query("termosBusca") query: String): List<BusLineDto>

    @GET("Parada/Buscar")
    suspend fun getBusStopWithAddressOrName(@Query("termosBusca") termosBusca: String): List<BusStopDto>

    @GET("Parada/BuscarParadasPorLinha")
    suspend fun getBusByBusLineId(
        @Query("codigoLinha") codigoLinha: String
    ): List<BusStopDto>

    @GET("Previsao/Parada")
    suspend fun getForecastWithBusStopCode(@Query("codigoParada") codigoParada: String) : BusStopPredictionDto?
}