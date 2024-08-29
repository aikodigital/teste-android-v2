package br.com.aiko.projetoolhovivo.data.service.auth

import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun auth(token: String): Result<Boolean> {
        return try {
            val authService: AuthService = retrofit.create(AuthService::class.java)
            val response = authService.authorize(token)
            Result.success(response)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}