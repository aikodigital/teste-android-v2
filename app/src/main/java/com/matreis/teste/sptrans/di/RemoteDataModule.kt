package com.matreis.teste.sptrans.di

import com.matreis.teste.sptrans.data.api.SpTransService
import com.matreis.teste.sptrans.data.datasource.arrivaltime.ArrivalTimeRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.arrivaltime.ArrivalTimeRemoteDataSourceImp
import com.matreis.teste.sptrans.data.datasource.auth.AuthRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.auth.AuthRemoteDataSourceImp
import com.matreis.teste.sptrans.data.datasource.busstop.BusStopRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.busstop.BusStopRemoteDataSourceImp
import com.matreis.teste.sptrans.data.datasource.lines.LinesRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.lines.LinesRemoteDataSourceImp
import com.matreis.teste.sptrans.data.datasource.roadspeed.RoadSpeedRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.roadspeed.RoadSpeedRemoteDataSourceImp
import com.matreis.teste.sptrans.data.datasource.vehicleposition.VehiclePositionRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.vehicleposition.VehiclePositionRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideArrivalTimeRemoteDataSource(service: SpTransService): ArrivalTimeRemoteDataSource {
        return ArrivalTimeRemoteDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun provideBusStopRemoteDataSource(service: SpTransService): BusStopRemoteDataSource {
        return BusStopRemoteDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun provideLinesRemoteDataSource(service: SpTransService): LinesRemoteDataSource {
        return LinesRemoteDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun provideVehiclePositionRemoteDataSource(service: SpTransService): VehiclePositionRemoteDataSource {
        return VehiclePositionRemoteDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(service: SpTransService): AuthRemoteDataSource {
        return AuthRemoteDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun provideRoadSpeedRemoteDataSource(service: SpTransService): RoadSpeedRemoteDataSource {
        return RoadSpeedRemoteDataSourceImp(service)
    }

}