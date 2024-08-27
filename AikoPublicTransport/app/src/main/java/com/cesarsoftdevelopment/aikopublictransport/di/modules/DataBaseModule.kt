package com.cesarsoftdevelopment.aikopublictransport.di.modules

import android.app.Application
import androidx.room.Room
import com.cesarsoftdevelopment.aikopublictransport.data.database.BusLineDao
import com.cesarsoftdevelopment.aikopublictransport.data.database.PublicTransportDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun providePublicTransportDatabase(application: Application) : PublicTransportDatabase {
        return Room.databaseBuilder(application, PublicTransportDatabase::class.java, "apt_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(articleDatabase: PublicTransportDatabase) : BusLineDao {
        return articleDatabase.getBusLineDao()
    }

}