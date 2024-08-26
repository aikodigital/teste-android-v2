package com.aiko.teste.sptrans.di

import com.aiko.teste.sptrans.data.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = "https://aiko-olhovivo-proxy.aikodigital.io/"

    @Provides
    @Named("apiToken")
    fun providesApiToken(): String =
        "66aae5541a229cf146b7e01a622079c818f22efbf8b9b3732e3abd8f01eed225"

    @Provides
    @Singleton
    fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)
}