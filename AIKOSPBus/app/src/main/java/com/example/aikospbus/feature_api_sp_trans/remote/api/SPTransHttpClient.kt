package com.example.aikospbus.feature_api_sp_trans.remote.api

import okhttp3.OkHttpClient

class SPTransHttpClient : OkHttpClient() {
    companion object {
        fun getClient(): OkHttpClient {
            return Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val url = original
                        .url
                        .newBuilder()
                        .build()
                    val request = original
                        .newBuilder()
                        .url(url)
                        .build()
                    chain.proceed(request)
                }
                .build()
        }
    }
}