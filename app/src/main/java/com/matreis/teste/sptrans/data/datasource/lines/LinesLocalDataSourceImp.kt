package com.matreis.teste.sptrans.data.datasource.lines

import com.matreis.teste.sptrans.data.db.LineDao
import com.matreis.teste.sptrans.domain.model.Line
import javax.inject.Inject

class LinesLocalDataSourceImp @Inject constructor(
    private val lineDao: LineDao
): LinesLocalDataSource {
    override suspend fun insertLine(line: Line) = lineDao.insertLine(line)
    override fun getAllLines() = lineDao.getAllLines()
    override suspend fun deleteAllLines() = lineDao.deleteAllLines()
    override suspend fun deleteLineByCode(lineCode: Long) = lineDao.deleteLineByCode(lineCode)
    override suspend fun getLineByCode(lineCode: Long) = lineDao.getLineByCode(lineCode)
}