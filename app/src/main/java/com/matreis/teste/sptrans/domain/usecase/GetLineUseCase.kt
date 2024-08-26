package com.matreis.teste.sptrans.domain.usecase

import com.matreis.teste.sptrans.data.repository.lines.LinesRepository
import javax.inject.Inject

class GetLineUseCase @Inject constructor(private val repository: LinesRepository) {
    suspend operator fun invoke(term: String) = repository.getLines(term)
    suspend operator fun invoke(term: String, direction: Int) = repository.getLinesByDirection(term, direction)
}