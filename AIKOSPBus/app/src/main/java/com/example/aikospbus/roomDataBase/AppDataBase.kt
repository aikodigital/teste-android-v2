package com.example.aikospbus.roomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDao
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.roomDataBase.roomConverters.BusLocationConverter
import com.example.aikospbus.feature_bus_corridor.data.data_source.BusCorridorDao
import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel
import com.example.aikospbus.feature_bus_lines.data.data_source.BusLinesDao
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import com.example.aikospbus.feature_bus_stops.data.data_source.BusStopsDao
import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel

@Database(
    entities = [BusLocationModel::class,BusCorridorModel::class,BusLinesModel::class,BusStopsModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BusLocationConverter::class
)
abstract class AppDataBase: RoomDatabase() {

    abstract fun BusLocationDao(): BusLocationDao

    abstract fun BusCorridorDao(): BusCorridorDao

    abstract fun BusLinesDao(): BusLinesDao

    abstract fun BusStopsDao(): BusStopsDao


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