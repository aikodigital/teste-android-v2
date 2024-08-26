package com.example.aikospbus.feature_api_sp_trans.remote.api

import retrofit2.HttpException

object CookieManager {
    var cookie: String = ""
    private var cookieTimestamp: Long? = null

    fun isCookieValid(): Boolean {
        val currentTime = System.currentTimeMillis()
        val expirationTime = cookieTimestamp?.plus(5 * 60 * 1000)
        return expirationTime != null && currentTime < expirationTime
    }

    suspend fun authentication() {
        try {
            val response =
                SPTransApi.retrofitService.authentication("604a216ace42329aa7581b9c6056a8a3dc2f574a680411928d5570478ca4c707")
            if (response.isSuccessful) {
                cookie = response.headers()["Set-Cookie"].orEmpty()
                cookieTimestamp = System.currentTimeMillis()

                val result = response.body()
                println("Login response: $result")
            } else {
                println("Erro de autenticação: ${response.errorBody()?.string()}")
            }
        } catch (e: HttpException) {
            println("Erro HTTP: ${e.message()}")
        } catch (e: Exception) {
            println("Erro: ${e.message}")
        }
    }
}