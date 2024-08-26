package com.matreis.teste.sptrans.domain.usecase

import com.matreis.teste.sptrans.data.repository.lines.LinesRepository
import com.matreis.teste.sptrans.domain.model.Line
import javax.inject.Inject

class SaveFavoriteLineUseCase @Inject constructor(
    private val repository: LinesRepository
) {
    suspend operator fun invoke(line: Line) = repository.insertFavoriteLine(line)
}