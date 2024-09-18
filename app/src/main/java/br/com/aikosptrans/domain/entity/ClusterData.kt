package br.com.aikosptrans.domain.entity

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class ClusterData(
    val location: LatLng,
    val name: String,
    val description: String?
) : ClusterItem {
    override fun getPosition(): LatLng = location

    override fun getTitle(): String = name

    override fun getSnippet(): String? = description

    override fun getZIndex(): Float = 1f
}