package com.cesarsoftdevelopment.aikopublictransport.di.modules

import com.cesarsoftdevelopment.aikopublictransport.data.repository.BusLinesRepositoryImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.EstimatedArrivalTimesRepositoryImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.StopsRepositoryImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.UserRepositoryImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.VehiclesPositionRepositoryImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.BusLinesRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.EstimatedArrivalTimesRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.StopsRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.UserRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.VehiclesPositionRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.BusLinesRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.EstimatedArrivalTimesRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.StopsRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.UserRepository
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.VehiclesPositionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource) : UserRepository {
        return UserRepositoryImpl(
            userRemoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideBusLinesRepository(busLinesRemoteDataSource : BusLinesRemoteDataSource) : BusLinesRepository {
        return BusLinesRepositoryImpl(
            busLinesRemoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideVehiclesPositionRepository(vehiclesPositionRemoteDataSource : VehiclesPositionRemoteDataSource) : VehiclesPositionRepository {
        return VehiclesPositionRepositoryImpl(
            vehiclesPositionRemoteDataSource
        )
    }

    @Singleton
    @Provides
    fun provideStopsRepository(stopsRemoteDataSource : StopsRemoteDataSource) : StopsRepository {
        return StopsRepositoryImpl(
            stopsRemoteDataSource
        )
    }


    @Singleton
    @Provides
    fun provideEstimatedArrivalTimesRepository(
        estimatedArrivalTimesRemoteDataSource : EstimatedArrivalTimesRemoteDataSource
    ) : EstimatedArrivalTimesRepository {
        return EstimatedArrivalTimesRepositoryImpl(
            estimatedArrivalTimesRemoteDataSource
        )
    }


}