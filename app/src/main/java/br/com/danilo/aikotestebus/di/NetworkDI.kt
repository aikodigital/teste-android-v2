package br.com.danilo.aikotestebus.di

import br.com.danilo.aikotestebus.BuildConfig
import br.com.danilo.aikotestebus.data.service.BusApiService
import br.com.danilo.aikotestebus.di.util.SaveCookies
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val module: Module = module {
        single {
            provideApiService(
                retrofit = provideRetrofitBuilder(),
                httpClient = providesClientBuilder()
            )
        }
    }

    private fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit
            .Builder()
            .baseUrl("https://aiko-olhovivo-proxy.aikodigital.io/")
            .addConverterFactory(provideConverter())
    }

    private fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    private fun providesClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.apply {
            addInterceptor(provideHttpLoggingInterceptor())
            addInterceptor(ProvidesTokenInterceptor())
            cookieJar(SaveCookies())
            connectTimeout(TIMEOUT_CONNECTION_MIN, TimeUnit.MINUTES)
        }
        return builder
    }

    private fun provideApiService(
        httpClient: OkHttpClient.Builder,
        retrofit: Retrofit.Builder,
    ): BusApiService {
        return retrofit
            .client(httpClient.build())
            .build()
            .create(BusApiService::class.java)
    }

    private const val TIMEOUT_CONNECTION_MIN = 1L
}

class ProvidesTokenInterceptor : Interceptor {
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