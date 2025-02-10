package hopeapps.dedev.sptrans.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class BusLine(
    val lineId: Int?,
    val isCircular: Boolean?,
    val firstLabelNumber: String?,
    val secondLabelNumber: Int?,
    val sense: Int?,
    val mainTerminal: String?,
    val secondaryTerminal: String?
)