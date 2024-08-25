package com.example.aikospbus.feature_bus_lines.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel

@Dao
interface BusLinesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusLines(busLinesModel: List<BusLinesModel>)

    @Query("SELECT * FROM busLines")
    suspend fun getBusLines(): BusLinesModel
}