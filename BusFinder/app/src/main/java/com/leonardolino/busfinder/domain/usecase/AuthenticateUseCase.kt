package com.leonardolino.busfinder.domain.usecase

import com.leonardolino.busfinder.domain.repository.BusRepository

class AuthenticateUseCase(private val repository: BusRepository) {
    suspend operator fun invoke(token: String) : Boolean {
        return repository.authenticate(token)
    }
}