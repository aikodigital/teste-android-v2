package com.matreis.teste.sptrans.data.repository.roadspeed

import com.matreis.teste.sptrans.data.datasource.roadspeed.RoadSpeedRemoteDataSource
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

interface RoadSpeedRepository  {
    suspend fun getRoadSpeed(): Response<ResponseBody>
}