package br.com.aikosptrans.domain.repository

interface LoginRepository {
    suspend fun authenticate(): Boolean
}