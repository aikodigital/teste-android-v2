package com.example.viewtab.network.repositories;

import com.example.viewtab.network.model.Previsao
import com.example.viewtab.network.modelNerwork.*
import com.example.viewtab.network.remoteDataSourse.PrevisionRemoteDataSource

import io.reactivex.Single

class PrevisaoRepository private constructor() {

    private val mPrevisionRemoteDataSource: PrevisionRemoteDataSource =
        PrevisionRemoteDataSource.getInstance()

    companion object {
        @Volatile
        private var INSTANCE: PrevisaoRepository? = null

        fun getInstance(): PrevisaoRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PrevisaoRepository().also { INSTANCE = it }
            }
        }
    }

    fun clearInstance() {
        INSTANCE = null
    }

    fun getParadaPrecisao(code: Long):
            Single<Resource<Previsao?>>? {
        return mPrevisionRemoteDataSource.getParadaPrecisao(code)
    }

}


