package com.matreis.teste.sptrans.di

import com.matreis.teste.sptrans.data.datasource.lines.LinesLocalDataSource
import com.matreis.teste.sptrans.data.datasource.lines.LinesLocalDataSourceImp
import com.matreis.teste.sptrans.data.db.LineDao
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
    fun provideLinesLocalDataSource(lineDao: LineDao): LinesLocalDataSource {
        return LinesLocalDataSourceImp(lineDao)
    }
}