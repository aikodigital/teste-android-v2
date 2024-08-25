package com.matreis.teste.sptrans.data.repository.roadspeed

import com.matreis.teste.sptrans.data.datasource.roadspeed.RoadSpeedRemoteDataSource
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RoadSpeedRepositoryImp @Inject constructor(
    private val roadSpeedRemoteDataSource: RoadSpeedRemoteDataSource
): RoadSpeedRepository {
    override suspend fun getRoadSpeed(): Response<ResponseBody> = roadSpeedRemoteDataSource.getRoadSpeed()
}