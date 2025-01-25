package br.com.danilo.aikotestebus.domain.usecase

import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.domain.mapper.toBusStopDetailList
import br.com.danilo.aikotestebus.domain.model.StopDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class BusStopByLineUseCase(
    private val repository: IBusRepository
) {

    suspend fun getBusStopByLine(idLine: Int): Flow<Result<List<StopDetail>>> {
        return flow {
            try {
                emit(Result.success(
                    repository.getBusStopByLine(idLine).toBusStopDetailList()
                ))
            } catch (ex: IOException) {
                emit(Result.failure(ex))
            } catch (ex: HttpException) {
                emit(Result.failure(ex))
            }
        }
    }
}