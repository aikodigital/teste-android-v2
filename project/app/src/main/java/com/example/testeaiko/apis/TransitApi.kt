package com.example.testeaiko.apis

import com.example.testeaiko.datamodels.ModelLinha
import com.example.testeaiko.datamodels.ModelParada
import com.example.testeaiko.datamodels.ModelPosicao
import com.example.testeaiko.datamodels.ModelPrevisaoParada
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TransitApi {
    @POST("Login/Autenticar")
    fun authenticate(@Query("token") token: String): Call<Boolean>

    @GET("Posicao")
    fun getPosicao(@Query("token") token: String): Call<ModelPosicao>

    @GET("Parada/Buscar")
    fun getParada(@Query("token") token: String,
                  @Query("termosBusca") termosBusca: String): Call<List<ModelParada>>

    @GET("Previsao/Parada")
    fun getPrevisaoParada(@Query("codigoParada") codigoParada: Int,
                          @Query("token") token: String): Call<ModelPrevisaoParada>

    @GET("Linha/Buscar")
    fun getLinhaBuscar(@Query("termosBusca") termosBusca: String,
                       @Query("token") token: String): Call<List<ModelLinha>>
}