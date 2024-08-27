package com.cesarsoftdevelopment.aikopublictransport.domain.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.StopItem
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
interface StopsRepository {
    suspend fun getStopsByLine(lineCode : Int) : Resource<List<StopItem>?>
}