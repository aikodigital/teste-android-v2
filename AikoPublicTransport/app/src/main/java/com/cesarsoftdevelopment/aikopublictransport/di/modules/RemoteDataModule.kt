package com.cesarsoftdevelopment.aikopublictransport.di.modules

import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.BusLinesRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.EstimatedArrivalTimesRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.StopsRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.UserRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.VehiclesPositionRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl.BusLinesRemoteDataSourceImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl.EstimatedArrivalTimesRemoteDataSourceImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl.StopsRemoteDataSourceImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl.UserRemoteDataSourceImpl
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl.VehiclesPositionRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(publicTransportApi: PublicTransportApi): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(publicTransportApi)
    }

    @Singleton
    @Provides
    fun provideBusLinesRemoteDataSource(publicTransportApi: PublicTransportApi): BusLinesRemoteDataSource {
        return BusLinesRemoteDataSourceImpl(publicTransportApi)
    }

    @Singleton
    @Provides
    fun provideVehiclesPositionRemoteDataSource(publicTransportApi: PublicTransportApi): VehiclesPositionRemoteDataSource {
        return VehiclesPositionRemoteDataSourceImpl(publicTransportApi)
    }

    @Singleton
    @Provides
    fun provideStopsRemoteDataSource(publicTransportApi: PublicTransportApi): StopsRemoteDataSource {
        return StopsRemoteDataSourceImpl(publicTransportApi)
    }

    @Singleton
    @Provides
    fun provideEstimatedArrivalTimesRemoteDataSource(publicTransportApi: PublicTransportApi): EstimatedArrivalTimesRemoteDataSource {
        return EstimatedArrivalTimesRemoteDataSourceImpl(publicTransportApi)
    }

}