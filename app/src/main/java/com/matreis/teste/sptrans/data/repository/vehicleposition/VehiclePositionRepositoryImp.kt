package com.matreis.teste.sptrans.data.repository.vehicleposition

import com.matreis.teste.sptrans.data.datasource.vehicleposition.VehiclePositionRemoteDataSource
import javax.inject.Inject

class VehiclePositionRepositoryImp @Inject constructor(
    private val vehiclePositionRemoteDataSource: VehiclePositionRemoteDataSource
) : VehiclePositionRepository {
}