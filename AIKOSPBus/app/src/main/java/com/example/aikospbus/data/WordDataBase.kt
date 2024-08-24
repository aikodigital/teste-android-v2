package com.example.aikospbus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.aikospbus.data.daos.WordDao
import com.example.aikospbus.data.models.Word
import com.example.aikospbus.data.roomConverters.WordConverter

@Database(
    entities = [Word::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    WordConverter::class
)
abstract class WordDataBase: RoomDatabase() {

    abstract fun wordDao(): WordDao


    companion object {

        @Volatile
        private var INSTANCE: WordDataBase? = null

        fun getDataBaseInstance(context: Context): WordDataBase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDataBase::class.java,
                    "word.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}