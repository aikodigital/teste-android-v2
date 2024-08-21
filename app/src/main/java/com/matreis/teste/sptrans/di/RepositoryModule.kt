package com.matreis.teste.sptrans.di

import com.matreis.teste.sptrans.data.datasource.arrivaltime.ArrivalTimeRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.auth.AuthRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.busstop.BusStopRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.lines.LinesRemoteDataSource
import com.matreis.teste.sptrans.data.datasource.vehicleposition.VehiclePositionRemoteDataSource
import com.matreis.teste.sptrans.data.preferences.UserPreferences
import com.matreis.teste.sptrans.data.repository.arrivaltime.ArrivalTimeRepository
import com.matreis.teste.sptrans.data.repository.arrivaltime.ArrivalTimeRepositoryImp
import com.matreis.teste.sptrans.data.repository.auth.AuthRepository
import com.matreis.teste.sptrans.data.repository.auth.AuthRepositoryImp
import com.matreis.teste.sptrans.data.repository.busstop.BusStopRepository
import com.matreis.teste.sptrans.data.repository.busstop.BusStopRepositoryImp
import com.matreis.teste.sptrans.data.repository.lines.LinesRepository
import com.matreis.teste.sptrans.data.repository.lines.LinesRepositoryImp
import com.matreis.teste.sptrans.data.repository.preferences.UserPreferencesRepository
import com.matreis.teste.sptrans.data.repository.preferences.UserPreferencesRepositoryImp
import com.matreis.teste.sptrans.data.repository.vehicleposition.VehiclePositionRepository
import com.matreis.teste.sptrans.data.repository.vehicleposition.VehiclePositionRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(remoteDataSource: AuthRemoteDataSource): AuthRepository {
        return AuthRepositoryImp(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideArrivalTimeRepository(remoteDataSource: ArrivalTimeRemoteDataSource): ArrivalTimeRepository {
        return ArrivalTimeRepositoryImp(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideBusStopRepository(remoteDataSource: BusStopRemoteDataSource): BusStopRepository {
        return BusStopRepositoryImp(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLinesRepository(remoteDataSource: LinesRemoteDataSource): LinesRepository {
        return LinesRepositoryImp(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideVehiclePositionRepository(remoteDataSource: VehiclePositionRemoteDataSource): VehiclePositionRepository {
        return VehiclePositionRepositoryImp(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(userPreferences: UserPreferences): UserPreferencesRepository {
        return UserPreferencesRepositoryImp(userPreferences)
    }

}