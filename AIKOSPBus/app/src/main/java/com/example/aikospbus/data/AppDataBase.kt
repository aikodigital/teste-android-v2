package com.example.aikospbus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDao
import com.example.aikospbus.feature_bus_location.domain.model.BusLocation
import com.example.aikospbus.data.roomConverters.WordConverter

@Database(
    entities = [BusLocation::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    WordConverter::class
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun BusLocationDao(): BusLocationDao


    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDataBaseInstance(context: Context): AppDataBase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}