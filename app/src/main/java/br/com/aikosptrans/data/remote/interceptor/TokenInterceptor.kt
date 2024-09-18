package br.com.aikosptrans.data.remote.interceptor

import br.com.aikosptrans.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url

        val newUrl: HttpUrl = originalHttpUrl
            .newBuilder()
            .addQueryParameter(
                name = "token",
                value = BuildConfig.API_TOKEN
            )
            .build()

        val requestBuilder = originalRequest
            .newBuilder()
            .url(newUrl)

        return chain.proceed(requestBuilder.build())
    }
}