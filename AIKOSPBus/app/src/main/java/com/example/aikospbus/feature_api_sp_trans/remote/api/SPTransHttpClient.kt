package com.example.aikospbus.feature_api_sp_trans.remote.api

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient

object SPTransHttpClient {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(object : CookieJar {
                private val cookieStore: MutableMap<String, List<Cookie>> = mutableMapOf()

                override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                    cookieStore[url.host] = cookies
                }

                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    return cookieStore[url.host] ?: listOf()
                }
            })
            .build()
    }
}
