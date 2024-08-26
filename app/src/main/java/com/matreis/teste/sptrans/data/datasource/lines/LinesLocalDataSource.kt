package com.matreis.teste.sptrans.data.datasource.lines

import androidx.lifecycle.LiveData
import com.matreis.teste.sptrans.domain.model.Line

interface LinesLocalDataSource {
    suspend fun insertLine(line: Line)
    fun getAllLines(): LiveData<List<Line>>
    suspend fun deleteAllLines()
    suspend fun deleteLineByCode(lineCode: Long)
    suspend fun getLineByCode(lineCode: Long): LiveData<Line>
}