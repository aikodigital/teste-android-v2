package com.matreis.teste.sptrans.di

import com.google.gson.Gson
import com.matreis.teste.sptrans.BuildConfig
import com.matreis.teste.sptrans.data.api.AddCookieInterceptor
import com.matreis.teste.sptrans.data.api.AuthAuthenticator
import com.matreis.teste.sptrans.data.api.SpTransService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(oktHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(oktHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authenticator: AuthAuthenticator, addCookieInterceptor: AddCookieInterceptor): OkHttpClient {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(addCookieInterceptor)
            .authenticator(authenticator)
            .build()

        return okHttpClient
    }

    @Provides
    @Singleton
    fun provideSpTransService(retrofit: Retrofit): SpTransService {
        return retrofit.create(SpTransService::class.java)
    }

}