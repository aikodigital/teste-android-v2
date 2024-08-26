package com.matreis.teste.sptrans.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matreis.teste.sptrans.domain.model.Line

@Dao
interface LineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLine(line: Line)

    @Query("SELECT * FROM line")
    fun getAllLines(): LiveData<List<Line>>

    @Query("DELETE FROM line")
    suspend fun deleteAllLines()

    @Query("DELETE FROM line WHERE codLine = :lineCode")
    suspend fun deleteLineByCode(lineCode: Long)

    @Query("SELECT * FROM line WHERE codLine = :lineCode")
    fun getLineByCode(lineCode: Long): LiveData<Line>

}