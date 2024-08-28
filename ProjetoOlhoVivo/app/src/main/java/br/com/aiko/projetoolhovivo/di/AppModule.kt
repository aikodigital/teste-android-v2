package br.com.aiko.projetoolhovivo.di

import android.app.Application
import br.com.aiko.projetoolhovivo.R
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

@Module
class AppModule {
    @Singleton
    @Provides
    fun providesRetrofit(application: Application): Retrofit {
        val okHttpClient =
            OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerModule(KotlinModule())))
            .baseUrl(application.getString(R.string.app_url_api_sp_trans))
            .build()
    }
}