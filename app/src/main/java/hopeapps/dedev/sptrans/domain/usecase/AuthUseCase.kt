package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.repository.SearchRepository

class AuthUseCase(private val repository: SearchRepository) {
    suspend operator fun invoke(): Result<Unit> {
        return try {
            val isAuthenticated = repository.authApi()
            if (isAuthenticated) {
                Result.success(Unit)
            } else {
                Result.failure(DomainException.AuthenticationException())
            }
        } catch (e: Exception) {
            Result.failure(DomainException.UnknownException())
        }
    }
}