package com.cesarsoftdevelopment.aikopublictransport.di.modules

import com.cesarsoftdevelopment.aikopublictransport.data.database.BusLineDao
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.TransportInfoLocalDataSource
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl.TransportInfoLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideNewsLocalDataSource(busLineDao: BusLineDao): TransportInfoLocalDataSource {
        return TransportInfoLocalDataSourceImpl(busLineDao)
    }

}