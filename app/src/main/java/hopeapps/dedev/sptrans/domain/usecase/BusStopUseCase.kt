package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.repository.SearchRepository

class BusStopUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): Result<List<BusStop>> {
        return try {
            return Result.success(repository.searchBusStop(query))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(DomainException.UnknownException())
        }
    }
}