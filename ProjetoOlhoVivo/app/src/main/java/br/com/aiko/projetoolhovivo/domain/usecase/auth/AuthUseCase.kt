package br.com.aiko.projetoolhovivo.domain.usecase.auth

import br.com.aiko.projetoolhovivo.data.service.auth.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(token: String): Result<Boolean> = repository.auth(token)
}