package hopeapps.dedev.sptrans.presentation.maps.cluster

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class ClusterBusLine(
    val lat: Double,
    val long: Double
): ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(long, lat)
    }

    override fun getTitle(): String = ""

    override fun getSnippet(): String = ""

    override fun getZIndex(): Float = 1f
}