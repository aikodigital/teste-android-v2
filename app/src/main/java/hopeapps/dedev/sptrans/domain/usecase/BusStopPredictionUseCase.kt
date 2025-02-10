package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.repository.SearchRepository

class BusStopPredictionUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(idBusStop: Int): Result<List<BusPrediction>> {
        return try {
            Result.success(repository.searchBusStopPrediction(idBusStop))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(DomainException.UnknownException())
        }
    }
}