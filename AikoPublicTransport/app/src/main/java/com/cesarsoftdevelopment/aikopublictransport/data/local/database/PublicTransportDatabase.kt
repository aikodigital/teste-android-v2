package com.cesarsoftdevelopment.aikopublictransport.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BusLineEntity::class], version = 1, exportSchema = false)
abstract class PublicTransportDatabase : RoomDatabase() {
    abstract fun getBusLineDao(): BusLineDao

}