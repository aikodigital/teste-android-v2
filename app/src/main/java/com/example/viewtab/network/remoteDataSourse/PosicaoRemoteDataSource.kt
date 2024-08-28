package com.example.viewtab.network.remoteDataSourse

import com.example.viewtab.network.model.Posicao
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.service.PosicaoVeiculoService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class PosicaoRemoteDataSource: BaseRemoteDataSourse() {

    companion object {
        @Volatile
        private var INSTANCE: PosicaoRemoteDataSource? = null

        fun getInstance(): PosicaoRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PosicaoRemoteDataSource().also { INSTANCE = it }
            }
        }
    }

    fun clearInstance() {
        INSTANCE = null
    }
/*
    fun getPosisao(isRepeate:Boolean = false): Single<Resource<Posicao?>?>? {
        val mainService: PosicaoVeiculoService = getMainService(PosicaoVeiculoService::class.java)

        return mainService.getPosisao()
            ?.observeOn(Schedulers.computation())
            ?.onErrorReturn { wrapInErrorResponse(it) }
            ?.map {
                this.isUnauthorizedResponse(it,isRepeate){
                    getPosisao(true)?.blockingGet()
                }
            }
    }*/

}