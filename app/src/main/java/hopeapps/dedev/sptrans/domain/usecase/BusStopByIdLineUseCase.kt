package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.data.model.BusStop
import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.exceptions.mapError
import hopeapps.dedev.sptrans.domain.repository.SearchRepository

class BusStopByIdLineUseCase(
    private val repository: SearchRepository

) {
    suspend operator fun invoke(idLine: Int): Result<List<BusStop>> {
        return try {
            when (val response = repository.searchBusStopByBusLineId(idLine)) {
                is ApiResponse.Success -> Result.success(response.data)
                is ApiResponse.Error -> Result.failure(mapError(response))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(DomainException.UnknownException())
        }
    }
}