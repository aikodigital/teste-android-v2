package br.com.danilo.aikotestebus.domain.mapper

import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.domain.model.LineDetail

fun List<LineDetailResponse?>.toLineDetailList(): List<LineDetail> {
    return this.map { lineDetailResponse ->
        lineDetailResponse?.toLineDetail() ?: LineDetail(
            lineId = 0,
            isCircular = false,
            firstLabelNumber = "",
            secondLabelNumber = 0,
            meaning = 0,
            mainTerminal = "",
            secondaryTerminal = ""
        )
    }
}

fun LineDetailResponse?.toLineDetail() : LineDetail {
    return LineDetail(
        lineId = this?.lineId.handleOptional(),
        isCircular = this?.isCircular.handleOptional(),
        firstLabelNumber = this?.firstLabelNumber.handleOptional(),
        secondLabelNumber = this?.secondLabelNumber.handleOptional(),
        meaning = this?.meaning.handleOptional(),
        mainTerminal = this?.mainTerminal.handleOptional(),
        secondaryTerminal = this?.secondaryTerminal.handleOptional()
    )
}

fun String?.handleOptional() = this ?: ""
fun Float?.handleOptional() = this ?: 0f
fun Int?.handleOptional() = this ?: 0
fun Boolean?.handleOptional() = this ?: false