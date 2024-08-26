package com.example.aikospbus.feature_bus_stop_prediction.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel

@Dao
interface StopPredictionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStopPrediction(stopPredictionModel: StopPredictionModel)

    @Query("SELECT * FROM stopPrediction")
    suspend fun getStopPrediction(): List<StopPredictionModel>?
}