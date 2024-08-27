package com.cesarsoftdevelopment.aikopublictransport.domain.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
interface VehiclesPositionRepository {
    suspend fun getVehiclesPositionByLine(lineCode : Int) : Resource<VehiclePosition?>
}