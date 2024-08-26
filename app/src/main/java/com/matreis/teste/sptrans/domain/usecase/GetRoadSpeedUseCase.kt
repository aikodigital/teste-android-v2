package com.matreis.teste.sptrans.domain.usecase

import com.matreis.teste.sptrans.data.repository.roadspeed.RoadSpeedRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class GetRoadSpeedUseCase @Inject constructor(
    private val repository: RoadSpeedRepository
) {
    suspend operator fun invoke() = repository.getRoadSpeed()
}