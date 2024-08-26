package com.matreis.teste.sptrans.domain.usecase

import com.matreis.teste.sptrans.data.repository.lines.LinesRepository
import javax.inject.Inject

class GetFavoritesLinesUseCase @Inject constructor(
    private val repository: LinesRepository
) {
    operator fun invoke() = repository.getAllFavoriteLines()
}