package com.example.aikospbus.feature_bus_stops.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel

@Dao
interface BusStopsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusStops(busStopsModel: List<BusStopsModel>)

    @Query("SELECT * FROM busStops")
    suspend fun getBusStops(): List<BusStopsModel>?
}