package com.aiko.teste.sptrans.data.repositories

import com.aiko.teste.sptrans.data.APIService
import com.aiko.teste.sptrans.data.objects.BusLine
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusLinesRepository @Inject constructor(private val apiService: APIService): BaseRepository() {
    fun getBusLines(searchTerms: String): Result<List<BusLine>> {
        return makeApiCall { apiService.getBusLines(searchTerms).execute() }
    }

    fun getBusPositions(busLineCode: String): Result<BusLine> {
        return makeApiCall { apiService.getBusPositionsFromLine(busLineCode).execute() }
    }
}