package com.matreis.teste.sptrans.di

import com.google.gson.Gson
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.matreis.teste.sptrans.BuildConfig
import com.matreis.teste.sptrans.data.api.AuthAuthenticator
import com.matreis.teste.sptrans.data.api.SpTransService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
    fun provideOkHttpClient(authenticator: AuthAuthenticator): OkHttpClient {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
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