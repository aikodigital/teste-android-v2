package com.example.viewtab.network.remoteDataSourse

import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.service.ParadasService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class ParadasRemoteDataSource: BaseRemoteDataSourse() {

    companion object {
        @Volatile
        private var INSTANCE: ParadasRemoteDataSource? = null

        fun getInstance(): ParadasRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ParadasRemoteDataSource().also { INSTANCE = it }
            }
        }
    }

    fun clearInstance() {
        INSTANCE = null
    }

    fun getBuscar(termo:String,isRepeate:Boolean =false): Single<Resource<List<Parada?>?>>? {
        val mainService: ParadasService = getMainService(ParadasService::class.java)

        return mainService.getBuscar(termo)
            ?.observeOn(Schedulers.computation())
            ?.onErrorReturn { wrapInErrorResponse(it) }
            ?.map {
                isUnauthorizedResponse(it,isRepeate){
                    getBuscar(termo,true)?.blockingGet()
            }}
           // ?.map { proccessResponse(it) }
    }

    fun getBuscarLinha(code:Long,isRepeate:Boolean =false): Single<Resource<List<Parada?>?>>? {
        val mainService: ParadasService = getMainService(ParadasService::class.java)

        return mainService.getBuscarLinha(code)
            ?.observeOn(Schedulers.computation())
            ?.onErrorReturn { wrapInErrorResponse(it) }
            ?.map { isUnauthorizedResponse(it,isRepeate){
                getBuscarLinha(code,true)?.blockingGet()
            }}
        // ?.map { proccessResponse(it) }
    }
}