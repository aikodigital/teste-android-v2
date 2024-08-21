package com.matreis.teste.sptrans.data.datasource.vehicleposition

import com.matreis.teste.sptrans.data.api.SpTransService
import javax.inject.Inject

class VehiclePositionRemoteDataSourceImp @Inject constructor(
    private val service: SpTransService
): VehiclePositionRemoteDataSource {
}