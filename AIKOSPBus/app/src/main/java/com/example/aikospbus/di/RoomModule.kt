package com.example.aikospbus.di

import android.content.Context
import com.example.aikospbus.data.AppDataBase
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDao
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDataSource
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationLocalDataSource
import com.example.aikospbus.feature_bus_location.data.repository.BusLocationRepository
import com.example.aikospbus.feature_bus_location.data.repository.BusLocationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.getDataBaseInstance(context)
    }

    @Provides
    fun BusLocationDao(appDataBase: AppDataBase) : BusLocationDao {
        return appDataBase.BusLocationDao()
    }

    @Provides
    fun provideLocalBusLocationDataSource(busLocationDao: BusLocationDao) : BusLocationDataSource {
        return BusLocationLocalDataSource(busLocationDao)
    }

    @Provides
    fun BusLocationRepository(dataSource: BusLocationDataSource): BusLocationRepository {
        return BusLocationRepositoryImpl(dataSource)
    }
}