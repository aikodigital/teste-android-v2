package br.com.danilo.aikotestebus.domain.usecase

import retrofit2.HttpException
import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.domain.mapper.toLineDetailList
import br.com.danilo.aikotestebus.domain.model.LineDetail
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LineBusDetailUseCase(
    private val repository: IBusRepository
) {

    suspend fun getBusLine(query: String): Flow<Result<List<LineDetail>>> {
        return flow {
            try {
                emit(Result.success(
                    repository.getBusLine(query).toLineDetailList()
                ))
            } catch (ex: IOException) {
                emit(Result.failure(ex))
            } catch (ex: HttpException) {
                emit(Result.failure(ex))
            }
        }
    }
}