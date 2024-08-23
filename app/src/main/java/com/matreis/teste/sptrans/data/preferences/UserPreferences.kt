package com.matreis.teste.sptrans.data.preferences

interface UserPreferences {
    suspend fun getCookie(): String
    suspend fun setCookie(cookie: String)
}