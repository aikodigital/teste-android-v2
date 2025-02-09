package hopeapps.dedev.sptrans.domain.models

import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class MapPoint {
    abstract val latitude: Double
    abstract val longitude: Double
    val position: LatLng get() = LatLng(latitude, longitude)
}

@Serializable
@SerialName("dynamic")
data class DynamicPoint(
    override val latitude: Double,
    override val longitude: Double,
    val id: String,
    val name: String
) : MapPoint()

@Serializable
@SerialName("static")
data class StaticPoint(
    override val latitude: Double,
    override val longitude: Double,
    val name: String
) : MapPoint()

@Serializable
@SerialName("action")
data object ActionPoint : MapPoint() {
    override val latitude: Double get() = 0.0
    override val longitude: Double get() = 0.0
}