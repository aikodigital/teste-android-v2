package com.matreis.teste.sptrans.data.datasource.roadspeed

import com.matreis.teste.sptrans.data.api.SpTransService
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RoadSpeedRemoteDataSourceImp @Inject constructor(
    private val spTransService: SpTransService
) : RoadSpeedRemoteDataSource {
    override suspend fun getRoadSpeed(): Response<ResponseBody> = spTransService.getRoadSpeed()
}