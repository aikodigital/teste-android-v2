package com.example.viewtab.network.repositories;

import com.example.viewtab.network.model.Parada
import com.example.viewtab.network.modelNerwork.*
import com.example.viewtab.network.remoteDataSourse.ParadasRemoteDataSource

import io.reactivex.Single

class ParadasRepository private constructor() {

    private val mParadasRemoteDataSource: ParadasRemoteDataSource =
        ParadasRemoteDataSource.getInstance()

    companion object {
        @Volatile
        private var INSTANCE: ParadasRepository? = null

        fun getInstance(): ParadasRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ParadasRepository().also { INSTANCE = it }
            }
        }
    }

    fun clearInstance() {
        INSTANCE = null
    }

    fun getBuscar(termo: String):
            Single<Resource<List<Parada?>?>>? {
        return mParadasRemoteDataSource.getBuscar(termo)
    }

    fun getPullRequestList(code: Int):
            Single<Resource<List<Parada?>?>>? {
        return mParadasRemoteDataSource.getBuscarLinha(code)
    }
}


