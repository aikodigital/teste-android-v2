package com.example.aikospbus.di

import android.content.Context
import com.example.aikospbus.data.WordDataBase
import com.example.aikospbus.data.daos.WordDao
import com.example.aikospbus.data.daos.WordDataSource
import com.example.aikospbus.data.daos.WordLocalDataSource
import com.example.aikospbus.data.repository.WordRepository
import com.example.aikospbus.data.repository.WordRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideWordDataBase(@ApplicationContext context: Context): WordDataBase {
        return WordDataBase.getDataBaseInstance(context)
    }

    @Provides
    fun wordDao(wordDataBase: WordDataBase) : WordDao {
        return wordDataBase.wordDao()
    }

    @Provides
    fun provideLocalWordDataSource(wordDao: WordDao) : WordDataSource {
        return WordLocalDataSource(wordDao)
    }

    @Provides
    fun wordRepository(dataSource: WordDataSource): WordRepository {
        return WordRepositoryImpl(dataSource)
    }
}