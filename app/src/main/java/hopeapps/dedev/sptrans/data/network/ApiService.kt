package hopeapps.dedev.sptrans.data.network

import hopeapps.dedev.sptrans.data.model.BusLine
import hopeapps.dedev.sptrans.data.model.BusStop
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @POST("Login/Autenticar")
    suspend fun authApi() : Boolean

    @GET("Linha/Buscar")
    suspend fun getBusLinesWithNameOrNumber(@Query("termosBusca") query: String): List<BusLine>

    @GET("Parada/Buscar")
    suspend fun getBusStopWithAddressOrName(@Query("termosBusca") termosBusca: String): List<BusStop>

    @GET("Parada/BuscarParadasPorLinha")
    suspend fun getBusByBusLineId(
        @Query("codigoLinha") codigoLinha: String
    ): List<BusStop>
}