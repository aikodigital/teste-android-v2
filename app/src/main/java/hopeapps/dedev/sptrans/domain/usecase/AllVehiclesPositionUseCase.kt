package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.domain.repository.SearchRepository

class AllVehiclesPositionUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(): Result<List<DynamicPoint>> {
        return try {
            Result.success(repository.searchAllBusMapPoints())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(DomainException.UnknownException())
        }
    }
}