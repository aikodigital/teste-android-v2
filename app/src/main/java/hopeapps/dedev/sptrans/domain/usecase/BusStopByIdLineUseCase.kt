package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.repository.SearchRepository

class BusStopByIdLineUseCase(
    private val repository: SearchRepository

) {
    suspend operator fun invoke(idLine: Int): Result<List<BusStop>> {
        return try {
            Result.success(repository.searchBusStopByBusLineId(idLine))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(DomainException.UnknownException())
        }
    }
}