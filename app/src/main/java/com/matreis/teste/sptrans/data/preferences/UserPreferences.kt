package com.matreis.teste.sptrans.data.preferences

interface UserPreferences {
    suspend fun getCookie(): String
    suspend fun setCookie(cookie: String)
    suspend fun setAutoUpdate(autoUpdate: Boolean)
    suspend fun getAutoUpdate(): Boolean
    suspend fun setAutoUpdateInterval(interval: Int)
    suspend fun getAutoUpdateInterval(): Int
}