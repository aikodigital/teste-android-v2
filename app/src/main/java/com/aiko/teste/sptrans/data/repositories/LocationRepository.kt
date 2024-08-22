package com.aiko.teste.sptrans.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.mapbox.geojson.Point
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocationRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {

    private fun isLocationPermissionGranted(): Boolean = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(onGetCurrentLocation: (Point) -> Unit) {
        if (!isLocationPermissionGranted()) {
            onGetCurrentLocation(Point.fromLngLat(-46.63, -23.55))
            return
        }

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null)
                    onGetCurrentLocation(Point.fromLngLat(location.longitude, location.latitude))
                else
                    onGetCurrentLocation(Point.fromLngLat(-46.63, -23.55))
            }
            .addOnFailureListener {
                onGetCurrentLocation(Point.fromLngLat(-46.63, -23.55))
            }
    }
}