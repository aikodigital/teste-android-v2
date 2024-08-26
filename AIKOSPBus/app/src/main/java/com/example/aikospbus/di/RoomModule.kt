package com.example.aikospbus.di

import android.content.Context
import com.example.aikospbus.roomDataBase.AppDataBase
import com.example.aikospbus.feature_bus_corridor.data.data_source.BusCorridorDao
import com.example.aikospbus.feature_bus_corridor.data.data_source.BusCorridorDataSource
import com.example.aikospbus.feature_bus_corridor.data.data_source.BusCorridorLocalDataSource
import com.example.aikospbus.feature_bus_corridor.data.remote.api.BusCorridorDataService
import com.example.aikospbus.feature_bus_corridor.data.repository.BusCorridorRepository
import com.example.aikospbus.feature_bus_corridor.data.repository.BusCorridorRepositoryImpl
import com.example.aikospbus.feature_bus_lines.data.data_source.BusLinesDao
import com.example.aikospbus.feature_bus_lines.data.data_source.BusLinesDataSource
import com.example.aikospbus.feature_bus_lines.data.data_source.BusLinesLocalDataSource
import com.example.aikospbus.feature_bus_lines.data.remote.api.BusLinesDataService
import com.example.aikospbus.feature_bus_lines.data.repository.BusLinesRepository
import com.example.aikospbus.feature_bus_lines.data.repository.BusLinesRepositoryImpl
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDao
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDataSource
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationLocalDataSource
import com.example.aikospbus.feature_bus_location.data.remote.api.BusLocationDataService
import com.example.aikospbus.feature_bus_location.data.repository.BusLocationRepository
import com.example.aikospbus.feature_bus_location.data.repository.BusLocationRepositoryImpl
import com.example.aikospbus.feature_bus_stop_prediction.data.data_source.StopPredictionDao
import com.example.aikospbus.feature_bus_stop_prediction.data.data_source.StopPredictionDataSource
import com.example.aikospbus.feature_bus_stop_prediction.data.data_source.StopPredictionLocalDataSource
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.api.StopPredictionDataService
import com.example.aikospbus.feature_bus_stop_prediction.data.repository.StopPredictionRepository
import com.example.aikospbus.feature_bus_stop_prediction.data.repository.StopPredictionRepositoryImpl
import com.example.aikospbus.feature_bus_stops.data.data_source.BusStopsDao
import com.example.aikospbus.feature_bus_stops.data.data_source.BusStopsDataSource
import com.example.aikospbus.feature_bus_stops.data.data_source.BusStopsLocalDataSource
import com.example.aikospbus.feature_bus_stops.data.remote.api.BusStopsDataService
import com.example.aikospbus.feature_bus_stops.data.repository.BusStopsRepository
import com.example.aikospbus.feature_bus_stops.data.repository.BusStopsRepositoryImpl
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
    fun BusLocationDao(appDataBase: AppDataBase): BusLocationDao {
        return appDataBase.BusLocationDao()
    }

    @Provides
    fun provideLocalBusLocationDataSource(busLocationDao: BusLocationDao): BusLocationDataSource {
        return BusLocationLocalDataSource(busLocationDao)
    }

    @Provides
    fun BusLocationRepository(dataSource: BusLocationDataSource): BusLocationRepository {
        return BusLocationRepositoryImpl(dataSource, BusLocationDataService.create())
    }

    @Provides
    fun BusCorridorDao(appDataBase: AppDataBase): BusCorridorDao {
        return appDataBase.BusCorridorDao()
    }

    @Provides
    fun provideLocalBusCorridorDataSource(busCorridorDao: BusCorridorDao): BusCorridorDataSource {
        return BusCorridorLocalDataSource(busCorridorDao)
    }

    @Provides
    fun BusCorridorRepository(dataSource: BusCorridorDataSource): BusCorridorRepository {
        return BusCorridorRepositoryImpl(dataSource, BusCorridorDataService.create())
    }

    @Provides
    fun BusLinesDao(appDataBase: AppDataBase): BusLinesDao {
        return appDataBase.BusLinesDao()
    }

    @Provides
    fun provideLocalBusLinesDataSource(busLinesDao: BusLinesDao): BusLinesDataSource {
        return BusLinesLocalDataSource(busLinesDao)
    }

    @Provides
    fun BusLinesRepository(dataSource: BusLinesDataSource): BusLinesRepository {
        return BusLinesRepositoryImpl(dataSource, BusLinesDataService.create())
    }

    @Provides
    fun BusStopsDao(appDataBase: AppDataBase): BusStopsDao {
        return appDataBase.BusStopsDao()
    }

    @Provides
    fun provideLocalBusStopsDataSource(busStopsDao: BusStopsDao): BusStopsDataSource {
        return BusStopsLocalDataSource(busStopsDao)
    }

    @Provides
    fun BusStopsRepository(dataSource: BusStopsDataSource): BusStopsRepository {
        return BusStopsRepositoryImpl(dataSource, BusStopsDataService.create())
    }

    @Provides
    fun stopsPredictionDao(appDataBase: AppDataBase): StopPredictionDao {
        return appDataBase.StopPredictionsDao()
    }

    @Provides
    fun provideLocalStopsPredictionDataSource(stopPredictionDao: StopPredictionDao): StopPredictionDataSource {
        return StopPredictionLocalDataSource(stopPredictionDao)
    }

    @Provides
    fun stopsPredictionsRepository(dataSource: StopPredictionDataSource): StopPredictionRepository {
        return StopPredictionRepositoryImpl(dataSource, StopPredictionDataService.create())
    }
}