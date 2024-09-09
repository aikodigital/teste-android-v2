package com.example.spbustracker.repository

import com.example.spbustracker.network.SPTransApiService

class AutenticacaoRepository(private val api: SPTransApiService) {

    suspend fun autenticar(token: String): Boolean {
        val response = api.autenticar(token)
        return response.isSuccessful
    }

}


