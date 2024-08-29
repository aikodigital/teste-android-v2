package br.com.aiko.projetoolhovivo.domain.usecase.position

import br.com.aiko.projetoolhovivo.data.model.position.PositionByListLine
import br.com.aiko.projetoolhovivo.data.model.position.PositionByVehicle
import br.com.aiko.projetoolhovivo.data.service.position.PositionRepository
import javax.inject.Inject

class PositionUseCase @Inject constructor(
    private val repository: PositionRepository
) {
    suspend fun getPositionByListLines(token: String): Result<PositionByListLine> =
        repository.getPositionByListLines(token)

    suspend fun getVehiclesPositionByCodeLine(token: String, codeLine: Int): Result<PositionByVehicle> =
        repository.getVehiclesPositionByCodeLine(token, codeLine)
}