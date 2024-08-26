package com.cesarsoftdevelopment.aikopublictransport.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*

object UserLocation {

    fun validatePermissions(permissions: Array<String>, activity: Activity, requestCode: Int): Boolean {

        val permissionsToRequest = mutableListOf<String>()

        for (permission in permissions) {
            val isPermissionGranted = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
            if (!isPermissionGranted) {
                permissionsToRequest.add(permission)
            }
        }

        if (permissionsToRequest.isNotEmpty()) {
            val permissionsArray = permissionsToRequest.toTypedArray()
            ActivityCompat.requestPermissions(activity, permissionsArray, requestCode)
            return false
        }

        return true
    }
}