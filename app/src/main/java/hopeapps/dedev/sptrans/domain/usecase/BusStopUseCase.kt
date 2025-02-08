package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import hopeapps.dedev.sptrans.domain.exceptions.mapError
import hopeapps.dedev.sptrans.domain.models.BusStop

class BusStopUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String): Result<List<BusStop>> {
        return try {
            when(val response = repository.searchBusStop(query)) {
                is ApiResponse.Error -> Result.failure(mapError(response))
                is ApiResponse.Success -> Result.success(response.data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(DomainException.UnknownException())
        }
    }
}