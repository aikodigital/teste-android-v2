package com.example.testeaiko.repositories

import com.example.testeaiko.BuildConfig
import com.example.testeaiko.datamodels.ModelLinha
import com.example.testeaiko.datamodels.ModelParada
import com.example.testeaiko.datamodels.ModelPosicao
import com.example.testeaiko.datamodels.ModelPrevisaoParada
import com.example.testeaiko.services.TransitApiService
import retrofit2.Response

class TransitApiRepository(
        private val apiService: TransitApiService,
) : ApiRepository() {


    fun getPosicao(callback: (Result<Response<ModelPosicao>>) -> Unit) {
        val call = apiService.createApi().getPosicao(BuildConfig.TRANSIT_API_KEY)
        makeApiCall(call, callback)
    }

    fun getPontos(callback: (Result<Response<List<ModelParada>>>) -> Unit) {
        val call = apiService.createApi().getParada(BuildConfig.TRANSIT_API_KEY, "")
        makeApiCall(call, callback)
    }

    fun getPrevisaoParada(codigoParada: Int, callback: (Result<Response<ModelPrevisaoParada>>) -> Unit) {
        val call =
            apiService.createApi().getPrevisaoParada(codigoParada, BuildConfig.TRANSIT_API_KEY)
        makeApiCall(call, callback)
    }

    fun getLinhaBuscar(termosBusca: String, callback: (Result<Response<List<ModelLinha>>>) -> Unit) {
        val call = apiService.createApi().getLinhaBuscar(termosBusca, BuildConfig.TRANSIT_API_KEY)
        makeApiCall(call, callback)
    }
}
