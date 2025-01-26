package br.com.danilo.aikotestebus.domain.usecase

import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.domain.mapper.toArrivalForecast
import br.com.danilo.aikotestebus.domain.model.ArrivalForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ArrivalForecastUseCase(
    private val repository: IBusRepository
) {

    fun getArrivalForecastTime(idStop: Int, idLine: Int) : Flow<Result<ArrivalForecast>> {
        return flow {
            try {
                val arrivalForecast = repository.getArrivalForecastTime(idStop, idLine)
                emit(Result.success(arrivalForecast.toArrivalForecast()))
            } catch (ex: IOException) {
                emit(Result.failure(ex))
            } catch (ex: HttpException) {
                emit(Result.failure(ex))
            }
        }
    }
}