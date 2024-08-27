package com.cesarsoftdevelopment.aikopublictransport.domain.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
interface BusLinesRepository {
    suspend fun getBusLines(termsSearch : String) : Resource<List<BusLineItem>>

}

