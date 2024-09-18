package br.com.aikosptrans.di

import br.com.aikosptrans.data.remote.interceptor.CookieManager
import br.com.aikosptrans.data.remote.interceptor.TokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object NetworkModule : KoinModule {
    override val module: Module = module {
        single {
            provideRetrofitBuilder(
                baseUrl = provideBaseUrl(),
                converter = provideConverter()
            )
        }

        single {
            providesClientBuilder(
                interceptors = provideInterceptors()
            )
        }
    }

    private fun providesClientBuilder(
        interceptors: List<Interceptor>
    ): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.apply {
             interceptors.forEach {
                 addInterceptor(it)
             }
            cookieJar(CookieManager())
            connectTimeout(TIMEOUT_CONNECTION_MIN, TimeUnit.MINUTES)
            readTimeout(TIMEOUT_READ_WRITE_SEC, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_READ_WRITE_SEC, TimeUnit.SECONDS)
        }
        return builder
    }

    private fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun provideInterceptors(): List<Interceptor> = listOf(
        provideHttpLoggingInterceptor(),
        TokenInterceptor()
    )

    private fun provideBaseUrl() : String = "https://api.olhovivo.sptrans.com.br/v2.1/"

    private fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }
    private fun provideRetrofitBuilder(
        baseUrl: String,
        converter: Converter.Factory
    ): Retrofit.Builder {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
    }

    private const val TIMEOUT_CONNECTION_MIN = 1L
    private const val TIMEOUT_READ_WRITE_SEC = 30L
}