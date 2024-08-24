package com.cesarsoftdevelopment.aikopublictransport.domain.repository

import com.cesarsoftdevelopment.aikopublictransport.utils.Resource

interface AuthenticateRepository {
    suspend fun authenticate(token : String) : Resource<Boolean>
}