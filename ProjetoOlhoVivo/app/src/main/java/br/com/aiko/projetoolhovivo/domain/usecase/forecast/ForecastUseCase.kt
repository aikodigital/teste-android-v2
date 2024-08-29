package br.com.aiko.projetoolhovivo.domain.usecase.forecast

import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastByLine
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastByStop
import br.com.aiko.projetoolhovivo.data.service.forecast.ForecastRepository
import javax.inject.Inject

class ForecastUseCase @Inject constructor(
    private val repository: ForecastRepository
) {
    suspend fun getForecastByStopCode(token: String, codeStop: Int): Result<ForecastByStop> =
        repository.getForecastByStopCode(token, codeStop)
    suspend fun getForecastByLineCode(token: String, codeLine: Int): Result<ForecastByLine> =
        repository.getForecastByLineCode(token, codeLine)
}