package com.example.aikospbus.di

import android.content.Context
import com.example.aikospbus.roomDataBase.AppDataBase
import com.example.aikospbus.feature_bus_corridor.data.data_source.BusCorridorDao
import com.example.aikospbus.feature_bus_corridor.data.data_source.BusCorridorDataSource
import com.example.aikospbus.feature_bus_corridor.data.data_source.BusCorridorLocalDataSource
import com.example.aikospbus.feature_bus_corridor.data.remote.api.BusCorridorDataService
import com.example.aikospbus.feature_bus_corridor.data.repository.BusCorridorRepository
import com.example.aikospbus.feature_bus_corridor.data.repository.BusCorridorRepositoryImpl
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDao
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDataSource
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationLocalDataSource
import com.example.aikospbus.feature_bus_location.data.remote.api.BusLocationDataService
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
        return BusLocationRepositoryImpl(dataSource,BusLocationDataService.create())
    }

    @Provides
    fun BusCorridorDao(appDataBase: AppDataBase): BusCorridorDao {
        return appDataBase.BusCorridorDao()
    }

    @Provides
    fun provideLocalBusCorridorDataSource(busCorridorDao: BusCorridorDao) : BusCorridorDataSource {
        return BusCorridorLocalDataSource(busCorridorDao)
    }

    @Provides
    fun BusCorridorRepository(dataSource: BusCorridorDataSource): BusCorridorRepository {
        return BusCorridorRepositoryImpl(dataSource, BusCorridorDataService.create())
    }

}