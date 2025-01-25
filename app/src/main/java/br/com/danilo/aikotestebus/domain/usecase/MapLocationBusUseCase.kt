package br.com.danilo.aikotestebus.domain.usecase

import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.domain.mapper.toBusesPosition
import br.com.danilo.aikotestebus.domain.model.BusesPosition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class MapLocationBusUseCase(
    private val repository: IBusRepository
) {
    suspend fun getBusesPosition(): Flow<Result<BusesPosition>> {
        return flow {
            try {
                emit(Result.success(
                    repository.getBusesPosition().toBusesPosition()
                ))
            } catch (ex: IOException) {
                emit(Result.failure(ex))
            }
        }
    }
}