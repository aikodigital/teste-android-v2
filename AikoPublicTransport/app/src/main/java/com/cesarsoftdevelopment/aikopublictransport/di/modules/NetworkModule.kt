package com.cesarsoftdevelopment.aikopublictransport.di.modules

import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import com.cesarsoftdevelopment.aikopublictransport.utils.AppStrings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providesInterceptor(): Interceptor {

        return Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun providesLoggingClient(authInterceptor: Interceptor) : OkHttpClient {

        val logger = HttpLoggingInterceptor.Logger { message ->
            println("LOG_CLIENT $message")
        }

        val loggingInterceptor = HttpLoggingInterceptor(logger)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitInstance(loggingClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppStrings.BASE_URL)
            .client(loggingClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun publicTransportApi(retrofit: Retrofit) : PublicTransportApi {
        return retrofit.create(PublicTransportApi::class.java)
    }

}