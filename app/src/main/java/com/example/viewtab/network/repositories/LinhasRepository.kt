package com.example.viewtab.network.repositories;

import com.example.viewtab.network.remoteDataSourse.LinhasRemoteDataSource
import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.modelNerwork.*

import io.reactivex.Single

class LinhasRepository private constructor() {

    private val mRepositoriesListRemoteDataSource: LinhasRemoteDataSource =
        LinhasRemoteDataSource.getInstance()

    companion object {
        @Volatile
        private var INSTANCE: LinhasRepository? = null

        fun getInstance(): LinhasRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LinhasRepository().also { INSTANCE = it }
            }
        }
    }

    fun clearInstance() {
        INSTANCE = null
    }

    fun getBuscar(termo: String):
            Single<Resource<List<Linha?>?>>? {
        return mRepositoriesListRemoteDataSource.getBuscar(termo)
    }

    fun getPullRequestList(termo: String, sentido: Int):
            Single<Resource<List<Linha?>?>>? {
        return mRepositoriesListRemoteDataSource.getBuscarLinhaSentido(termo, sentido)
    }
}


