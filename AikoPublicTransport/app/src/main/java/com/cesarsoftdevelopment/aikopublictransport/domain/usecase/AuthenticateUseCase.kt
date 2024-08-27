package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.UserRepository
class AuthenticateUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(token : String) = userRepository.authenticate(token)

}