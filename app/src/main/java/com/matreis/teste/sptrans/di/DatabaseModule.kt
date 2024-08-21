package com.matreis.teste.sptrans.di

import android.content.Context
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

}