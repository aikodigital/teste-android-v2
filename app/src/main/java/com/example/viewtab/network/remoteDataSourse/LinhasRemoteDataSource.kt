package com.example.viewtab.network.remoteDataSourse

import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.modelNerwork.Resource
import com.example.viewtab.network.service.LinhasService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class LinhasRemoteDataSource: BaseRemoteDataSourse() {

    companion object {
        @Volatile
        private var INSTANCE: LinhasRemoteDataSource? = null

        fun getInstance(): LinhasRemoteDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LinhasRemoteDataSource().also { INSTANCE = it }
            }
        }
    }

    fun clearInstance() {
        INSTANCE = null
    }

    fun getBuscar(termo:String): Single<Resource<List<Linha?>?>>? {
        val mainService: LinhasService = getMainService(LinhasService::class.java)

        return mainService.getBuscar(termo)
            ?.observeOn(Schedulers.computation())
            ?.onErrorReturn { wrapInErrorResponse(it) }
            ?.map { isUnauthorizedResponse(it){
                getBuscar(termo)?.blockingGet()
            }}
           // ?.map { proccessResponse(it) }
    }

    fun getBuscarLinhaSentido(termo:String,sentido: Int): Single<Resource<List<Linha?>?>>? {
        val mainService: LinhasService = getMainService(LinhasService::class.java)

        return mainService.getBuscarLinhaSentido(termo,sentido)
            ?.observeOn(Schedulers.computation())
            ?.onErrorReturn { wrapInErrorResponse(it) }
            ?.map { isUnauthorizedResponse(it){
                getBuscarLinhaSentido(termo,sentido)?.blockingGet()
            }}
        // ?.map { proccessResponse(it) }
    }
}