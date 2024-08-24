package com.leonardolino.busfinder.di

import com.leonardolino.busfinder.data.api.ApiService
import com.leonardolino.busfinder.data.repository.BusRepositoryImpl
import com.leonardolino.busfinder.domain.repository.BusRepository
import com.leonardolino.busfinder.domain.usecase.AuthenticateUseCase
import com.leonardolino.busfinder.domain.usecase.GetBusStopsUseCase
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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCookieJar(): CookieJar {
        return object : CookieJar {
            private val cookieStore: MutableMap<HttpUrl, List<Cookie>> = HashMap()

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                cookieStore[url] = cookies
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                return cookieStore[url] ?: ArrayList()
            }
        }
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        cookieJar: CookieJar
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://aiko-olhovivo-proxy.aikodigital.io/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBusApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBusRepository(apiService: ApiService): BusRepository {
        return BusRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetBusStopsUseCase(repository: BusRepository): GetBusStopsUseCase {
        return GetBusStopsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAuthenticateUseCase(repository: BusRepository): AuthenticateUseCase {
        return AuthenticateUseCase(repository)
    }
}