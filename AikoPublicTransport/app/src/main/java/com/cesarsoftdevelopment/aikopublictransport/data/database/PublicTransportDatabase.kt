package com.cesarsoftdevelopment.aikopublictransport.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity

@Database(entities = [BusLineEntity::class], version = 1, exportSchema = false)
abstract class PublicTransportDatabase : RoomDatabase() {
    abstract fun getBusLineDao(): BusLineDao

}