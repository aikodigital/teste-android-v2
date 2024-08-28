package com.example.viewtab.network.remoteDataSourse

import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.model.Previsao
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.service.PrevisaoChegadaService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class PrevisionRemoteDataSource: BaseRemoteDataSourse() {

    companion object {
        @Volatile
        private var INSTANCE: PrevisionRemoteDataSource? = null

        fun getInstance(): PrevisionRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PrevisionRemoteDataSource().also { INSTANCE = it }
            }
        }
    }

    fun clearInstance() {
        INSTANCE = null
    }

    fun getParadaPrecisao(code:Long, isRepeate:Boolean =false): Single<Resource<Previsao?>>? {
        val mainService: PrevisaoChegadaService = getMainService(PrevisaoChegadaService::class.java)

        return mainService.getParadaPrecisao(code)
            ?.observeOn(Schedulers.computation())
            ?.onErrorReturn { wrapInErrorResponse(it) }
            ?.map {
                isUnauthorizedResponse(it,isRepeate){
                    getParadaPrecisao(code,true)?.blockingGet()
            }}
    }

}