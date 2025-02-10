package hopeapps.dedev.sptrans.di

import hopeapps.dedev.sptrans.BuildConfig
import hopeapps.dedev.sptrans.data.network.SaveCookies
import hopeapps.dedev.sptrans.data.network.ApiService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val module = module {
        single {
            provideApiService(
                retrofit = provideRetrofitBuilder(),
                httpClient = providesClientBuilder()
            )
        }
    }

    private const val BASE_URL = "https://api.olhovivo.sptrans.com.br/v2.1/"
    private const val CONNECTION_TIMEOUT_MINUTES = 1L

    private fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(provideConverter())
    }

    private fun provideConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    private fun providesClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(provideHttpLoggingInterceptor())
            addInterceptor(ProvidesTokenInterceptor())
            cookieJar(SaveCookies())
            connectTimeout(CONNECTION_TIMEOUT_MINUTES, TimeUnit.MINUTES)
        }
    }

    private fun provideApiService(
        httpClient: OkHttpClient.Builder,
        retrofit: Retrofit.Builder
    ): ApiService {
        return retrofit
            .client(httpClient.build())
            .build()
            .create(ApiService::class.java)
    }

    class ProvidesTokenInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest: Request = chain.request()
            val originalHttpUrl: HttpUrl = originalRequest.url

            val newUrl: HttpUrl = originalHttpUrl
                .newBuilder()
                .addQueryParameter("token", BuildConfig.API_KEY)
                .build()

            val requestBuilder = originalRequest
                .newBuilder()
                .url(newUrl)

            return chain.proceed(requestBuilder.build())
        }
    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }
}