package com.aiko.teste.sptrans.di

import com.aiko.teste.sptrans.data.APIService
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @Singleton
    @Provides
    fun provideBusStopsRepository(apiService: APIService): BusStopsRepository {
        return BusStopsRepository(apiService)
    }
}