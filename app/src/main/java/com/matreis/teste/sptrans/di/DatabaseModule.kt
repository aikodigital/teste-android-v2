package com.matreis.teste.sptrans.di

import android.content.Context
import androidx.room.Room
import com.matreis.teste.sptrans.data.db.SpTransDatabase
import com.matreis.teste.sptrans.data.preferences.UserPreferences
import com.matreis.teste.sptrans.data.preferences.UserPreferencesImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): UserPreferences {
        return UserPreferencesImp(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SpTransDatabase {
        return Room.databaseBuilder(
            context,
            SpTransDatabase::class.java,
            "sptrans_database"
        ).build()
    }

    @Provides
    fun provideLineDao(database: SpTransDatabase) = database.lineDao()

}