package com.example.viewtab.network.service

import com.example.viewtab.network.model.Posicao
import com.example.viewtab.network.model.Previsao
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PosicaoVeiculoService {

    @GET("Posicao")
    fun getPosisao(): Single<Response<Posicao?>?>?

}