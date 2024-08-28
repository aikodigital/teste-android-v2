package br.com.aiko.projetoolhovivo.domain.usecase.stop

import br.com.aiko.projetoolhovivo.data.model.stop.Stop
import br.com.aiko.projetoolhovivo.data.service.stop.StopRepository
import javax.inject.Inject

class StopUseCase @Inject constructor(
    private val repository: StopRepository
) {
    suspend fun getStops(token: String): Result<List<Stop>> =
        repository.getStops(token)
}