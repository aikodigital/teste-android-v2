package com.example.aikospbus.feature_bus_location.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel

@Dao
interface BusLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusLocation(busLocationModel: BusLocationModel)

    @Query("SELECT * FROM busLocation")
    suspend fun getBusLocation(): BusLocationModel

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBusLocation(busLocationModel: BusLocationModel)
}