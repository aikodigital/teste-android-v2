package com.example.aikospbus.feature_bus_corridor.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel

@Dao
interface BusCorridorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusCorridor(busCorridorModel: List<BusCorridorModel>)

    @Query("SELECT * FROM busCorridor")
    suspend fun getBusCorridor(): BusCorridorModel
}