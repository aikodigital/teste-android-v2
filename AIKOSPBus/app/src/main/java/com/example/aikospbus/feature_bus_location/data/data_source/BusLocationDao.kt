package com.example.aikospbus.feature_bus_location.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aikospbus.feature_bus_location.domain.model.BusLocation

@Dao
interface BusLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusLocation(busLocation: BusLocation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusLocationList(busLocationList: List<BusLocation>)

    @Query("SELECT * FROM busLocation")
    suspend fun getBusLocationWords(): List<BusLocation>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBusLocation(busLocation: BusLocation)
}