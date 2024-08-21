package com.aiko.teste.sptrans.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocationSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {

    private fun isLocationPermissionGranted(): Boolean = ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun isLocationAvailable(): Boolean {
        if (!isLocationPermissionGranted()) return false
        Log.d(
            "LocationSource",
            "fusedLocationProviderClient.locationAvailability.isSuccessful = ${fusedLocationProviderClient.locationAvailability.isSuccessful}"
        )
        return fusedLocationProviderClient.locationAvailability.isSuccessful
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(onGetCurrentLocation: (Point) -> Unit) {

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener {
                onGetCurrentLocation(Point.fromLngLat(it.longitude, it.latitude))
            }
            .addOnFailureListener {
                onGetCurrentLocation(Point.fromLngLat(-46.63, -23.55))
            }
    }
}