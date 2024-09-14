package br.com.aikosptrans.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit

inline fun <reified Service> service(
    retrofit: Retrofit.Builder,
    client: OkHttpClient.Builder
) : Lazy<Service> = lazy {
    retrofit
        .client(client.build())
        .build()
        .create(Service::class.java)
}