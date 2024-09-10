package com.example.spbustracker.ui.vehicles

import CustomInfoWindowAdapter
import android.annotation.SuppressLint
import android.content.Context
import com.example.spbustracker.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

@SuppressLint("PotentialBehaviorOverride")
class VehicleClusterRenderer(
    private val context: Context,
    private val googleMap: GoogleMap,
    clusterManager: ClusterManager<VehicleClusterItem>
) : DefaultClusterRenderer<VehicleClusterItem>(context, googleMap, clusterManager) {

    init {
        googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(context))
    }

    override fun onBeforeClusterItemRendered(
        item: VehicleClusterItem,
        markerOptions: com.google.android.gms.maps.model.MarkerOptions
    ) {
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_marker))
        markerOptions.title(item.title)
        markerOptions.snippet(item.snippet)
        googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(context))
        super.onBeforeClusterItemRendered(item, markerOptions)
    }
}
