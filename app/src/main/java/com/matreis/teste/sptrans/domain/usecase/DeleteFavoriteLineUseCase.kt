package com.matreis.teste.sptrans.domain.usecase

import com.matreis.teste.sptrans.data.repository.lines.LinesRepository
import javax.inject.Inject

class DeleteFavoriteLineUseCase @Inject constructor(
    private val repository: LinesRepository
) {
    suspend operator fun invoke(lineCode: Long) = repository.deleteFavoriteLineByCode(lineCode)
}