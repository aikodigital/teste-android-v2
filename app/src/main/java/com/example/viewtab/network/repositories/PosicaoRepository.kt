package com.example.viewtab.network.repositories;

import com.example.viewtab.network.model.Linha
import com.example.viewtab.network.model.Posicao
import com.example.viewtab.network.modelNerwork.*
import com.example.viewtab.network.remoteDataSourse.PosicaoRemoteDataSource

import io.reactivex.Single

class PosicaoRepository private constructor() {

    private val mPosicaoRemoteDataSource:PosicaoRemoteDataSource =
        PosicaoRemoteDataSource.getInstance()

    companion object {
        @Volatile
        private var INSTANCE: PosicaoRepository? = null

        fun getInstance(): PosicaoRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PosicaoRepository().also { INSTANCE = it }
            }
        }
    }

    fun clearInstance() {
        INSTANCE = null
    }

    fun getBuscar():
            Single<Resource<Posicao?>?>? {
        return mPosicaoRemoteDataSource.getPosisao()
    }

}


