package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.data.model.BusLine
import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import hopeapps.dedev.sptrans.domain.exceptions.mapError

class BusLineUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke(query: String): Result<List<BusLine>> {
        return try {
            when (val response = repository.searchBusLines(query)) {
                is ApiResponse.Success -> Result.success(response.data)
                is ApiResponse.Error -> Result.failure(mapError(response))
            }
        } catch (e: Exception) {
            Result.failure(DomainException.UnknownException())
        }
    }
}