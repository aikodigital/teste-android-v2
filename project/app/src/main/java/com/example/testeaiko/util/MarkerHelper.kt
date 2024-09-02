package com.example.testeaiko.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.testeaiko.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MarkerHelper(private val context: Context) {

    // Map to store markers and their assigned icons
    private val vehicleMarkers = mutableMapOf<LatLng, Marker>()
    private val stopMarkers = mutableMapOf<LatLng, Marker>()
    private var lastPolyline: Polyline? = null

    // Add a marker with a random icon if it doesn't exist
    fun addVehicleMarker(mMap: GoogleMap, latLng: LatLng, title: String, scaleFactor: Float) {
        val markerIcon = getBitmapDescriptorFromVector(R.drawable.bus_icon_1, scaleFactor)
        val marker = mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(title)
                .icon(markerIcon)
        )

        // Store the marker in the map
        marker?.let { vehicleMarkers[latLng] = it }
    }

    fun addStopIconMarker(mMap: GoogleMap, latLng: LatLng, stopCode: String, scaleFactor: Float) {
        val markerIcon = getBitmapDescriptorFromVector(R.drawable.bus_stop, scaleFactor)
        val marker = mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(stopCode)
                .icon(markerIcon)
        )

        // Store the marker in the map
        marker?.let { stopMarkers[latLng] = it }
    }

    // Helper function to convert a vector drawable to BitmapDescriptor with scaling
    private fun getBitmapDescriptorFromVector(vectorResId: Int, scaleFactor: Float): BitmapDescriptor {
        val vectorDrawable: Drawable? = ContextCompat.getDrawable(context, vectorResId)
        val originalWidth = vectorDrawable?.intrinsicWidth ?: 0
        val originalHeight = vectorDrawable?.intrinsicHeight ?: 0
        val scaledWidth = (originalWidth * scaleFactor).toInt()
        val scaledHeight = (originalHeight * scaleFactor).toInt()

        vectorDrawable?.setBounds(0, 0, scaledWidth, scaledHeight)
        val bitmap = Bitmap.createBitmap(
            scaledWidth,
            scaledHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable?.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun removeMarkers(iterator: MutableIterator<MutableMap.MutableEntry<LatLng, Marker>>) {
        while (iterator.hasNext()) {
            val entry = iterator.next()
            val marker = entry.value
            marker.remove()
            iterator.remove()
        }
    }

    fun removeAllMarkers() {
        removeMarkers(vehicleMarkers.entries.iterator())
        removeMarkers(stopMarkers.entries.iterator())
    }

    fun isStopMarker(marker: Marker): Boolean {
        return stopMarkers.containsValue(marker)
    }

    fun drawRoute(mMap: GoogleMap, points: String) {
        // Decode the polyline and draw the route on the map
        val polylinePoints = decodePolyline(points)
        val boundsBuilder = LatLngBounds.Builder()

        val polylineOptions = PolylineOptions().color(Color.BLUE).width(12f)
        for (latLng in polylinePoints) {
            polylineOptions.add(latLng)
            boundsBuilder.include(latLng)
        }
        lastPolyline?.remove()
        // Draw the polyline on the map
        val polyline = mMap.addPolyline(polylineOptions)
        lastPolyline = polyline
        // Move the camera to show the entire route
        val bounds = boundsBuilder.build()
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,
            100)) // 100 is padding in pixels
    }

    fun decodePolyline(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].code - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val point = LatLng(
                lat / 1E5,
                lng / 1E5
            )
            poly.add(point)
        }

        return poly
    }
}
