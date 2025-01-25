package br.com.danilo.aikotestebus.domain.model.entity

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class MapMarker(
    val titleText: String,
    val snippetText: String,
    val location: LatLng,
) : ClusterItem {

    override fun getPosition(): LatLng = location

    override fun getTitle(): String = titleText

    override fun getSnippet(): String = snippetText

    override fun getZIndex(): Float = 1f
}