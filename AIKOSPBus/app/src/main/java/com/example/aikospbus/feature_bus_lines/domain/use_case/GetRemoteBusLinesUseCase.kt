package com.example.aikospbus.feature_bus_lines.domain.use_case

import com.example.aikospbus.feature_bus_lines.data.repository.BusLinesRepository
import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteBusLinesUseCase @Inject constructor(private val busLinesRepository: BusLinesRepository) {

    operator fun invoke(cookie: String, searchTerms: String): Flow<Resource<BusLinesModel>> {
        return busLinesRepository.getRemoteBusLines(cookie,searchTerms)
    }
}