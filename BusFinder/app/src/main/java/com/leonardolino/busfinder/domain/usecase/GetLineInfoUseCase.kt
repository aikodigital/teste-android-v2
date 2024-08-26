package com.leonardolino.busfinder.domain.usecase

import com.leonardolino.busfinder.domain.model.LineInfo
import com.leonardolino.busfinder.domain.repository.BusRepository

class GetLineInfoUseCase(private val repository: BusRepository) {
    suspend operator fun invoke(terms: String, lineDirection: Int): List<LineInfo> {
        return repository.getLineInfo(terms, lineDirection)
    }
}