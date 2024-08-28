package br.com.aiko.projetoolhovivo.domain.usecase.line

import br.com.aiko.projetoolhovivo.data.model.line.Line
import br.com.aiko.projetoolhovivo.data.service.line.LineRepository
import javax.inject.Inject

class LineUseCase @Inject constructor(
    private val repository: LineRepository
) {
    suspend fun getLines(token: String): Result<List<Line>> =
        repository.getLines(token)
}