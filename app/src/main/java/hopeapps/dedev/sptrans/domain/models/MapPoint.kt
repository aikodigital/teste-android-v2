package hopeapps.dedev.sptrans.domain.models

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class MapPoint () {
    abstract val latitude: Double
    abstract val longitude: Double
}

@Serializable
@SerialName("dynamic")
data class DynamicPoint(
    override val latitude: Double,
    override val longitude: Double,
    val id: Int,
    val name: String,
    val lastUpdate: String,
    val accessible: Boolean,
    val prefix: Int
) : MapPoint(), ClusterItem {
    override fun getPosition(): LatLng = LatLng(latitude, longitude)
    override fun getTitle(): String = name
    override fun getSnippet(): String = ""
    override fun getZIndex(): Float = 1f
}

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