package com.cesarsoftdevelopment.aikopublictransport.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BusLineDao {
    @Query("SELECT * FROM bus_line")
    fun getAllBusLines(): Flow<List<BusLineEntity>>
    @Query("DELETE FROM bus_line WHERE lineCode = :code")
    fun deleteBusLineByCode(code: Int)
    @Query("DELETE FROM bus_line")
    fun deleteAllBusLines()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusLine(busLine: BusLineEntity)

}