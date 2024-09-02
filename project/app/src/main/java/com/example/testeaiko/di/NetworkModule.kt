package com.example.testeaiko.di

import com.example.testeaiko.SessionManager
import com.example.testeaiko.apis.TransitApi
import com.example.testeaiko.repositories.RoutesApiRepository
import com.example.testeaiko.repositories.TransitApiRepository
import com.example.testeaiko.services.RoutesApiService
import com.example.testeaiko.services.TransitApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.olhovivo.sptrans.com.br/v2.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideSessionManager(apiService: TransitApiService): SessionManager {
        return SessionManager(apiService)
    }

    @Provides
    @Singleton
    fun provideTransitApi(retrofit: Retrofit): TransitApi {
        return retrofit.create(TransitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransitApiRepository(apiService: TransitApiService): TransitApiRepository {
        return TransitApiRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideTransitApiService(okHttpClient: OkHttpClient): TransitApiService {
        return TransitApiService(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideRoutesApiService(okHttpClient: OkHttpClient): RoutesApiService {
        return RoutesApiService(okHttpClient)
    }

    @Provides
    @Singleton
    fun provideRoutesApiRepository(apiService: RoutesApiService): RoutesApiRepository {
        return RoutesApiRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideCookieJar(): CookieJar {
        return MyCookieJar()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cookieJar: CookieJar): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .addInterceptor(HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BASIC })
            .build()
    }

    class MyCookieJar : CookieJar {
        private val cookieStore = ConcurrentHashMap<String, MutableList<Cookie>>()

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            val currentCookies = cookieStore[url.host] ?: mutableListOf()
            val cookiesMap = currentCookies.associateBy { it.name }.toMutableMap()

            for (cookie in cookies) {
                if (cookie.value.isNotEmpty()) {
                    cookiesMap[cookie.name] = cookie
                }
            }
            cookieStore[url.host] = cookiesMap.values.toMutableList()
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> {
            return cookieStore[url.host] ?: emptyList()
        }
    }
}