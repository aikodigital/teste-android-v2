package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.repository.SearchRepository

class BusLineUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String): Result<List<BusLine>> {
        return try {
            Result.success(repository.searchBusLines(query))
        } catch (e: Exception) {
            Result.failure(DomainException.UnknownException())
        }
    }
}