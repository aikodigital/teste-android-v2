package com.matreis.teste.sptrans.data.api

import com.matreis.teste.sptrans.data.preferences.UserPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AddCookieInterceptor @Inject constructor(
    private val userPreferences: UserPreferences
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val request = chain.request().newBuilder()
            userPreferences.getCookie().let {
                request.addHeader("Cookie", it)
            }
            chain.proceed(request.build())
        }
    }

}