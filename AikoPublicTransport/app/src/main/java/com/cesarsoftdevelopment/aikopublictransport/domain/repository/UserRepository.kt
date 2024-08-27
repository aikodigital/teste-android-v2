package com.cesarsoftdevelopment.aikopublictransport.domain.repository

import com.cesarsoftdevelopment.aikopublictransport.utils.Resource

interface UserRepository {
    suspend fun authenticate(token : String) : Resource<Boolean>
}