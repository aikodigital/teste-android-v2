package com.matreis.teste.sptrans.data.datasource.roadspeed

import okhttp3.ResponseBody
import retrofit2.Response

interface RoadSpeedRemoteDataSource {
    suspend fun getRoadSpeed(): Response<ResponseBody>
}
