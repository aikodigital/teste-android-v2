package com.example.viewtab.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.viewtab.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

abstract class BaseMapFragment: Fragment(), OnMapReadyCallback  {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    protected var onInfoWindowClickListener: GoogleMap.OnInfoWindowClickListener? = null
    protected var mMap: GoogleMap? = null
    protected var myLocation: Location? = null

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onResume() {
        super.onResume()
        checkLocationPermission()

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun checkLocationPermission() {
        val safeContext = context ?: return
        val safeActivity = activity ?: return

        if (ContextCompat.checkSelfPermission(safeContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(safeContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(safeActivity, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), LOCATION_PERMISSION_REQUEST_CODE
            )
        }else{
            location()
        }
    }

    fun location() {
        val safeActivity = activity ?: return
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(safeActivity)
        getCurrentLocation()
    }

    abstract fun loadLocationResponse(location: Location?)

    private fun getCurrentLocation() {
        if (checkPermissionLocation())  return
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    loadLocationResponse(location)
                } else {
                    Toast.makeText(this.context, "Localização não disponível", Toast.LENGTH_LONG).show()
                }
            }
            ?.addOnFailureListener {
                Toast.makeText(this.context, "Erro ao pegar localização", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkPermissionLocation():Boolean{
        val safeContext = context ?: return false
        return ActivityCompat.checkSelfPermission(safeContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(safeContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

    // Tratamento da resposta do usuário
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                location()
            } else {
                Toast.makeText(this.context, "Permissões de localização são necessárias para o funcionamento da aplicação", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun setFocoCamera(latLng: LatLng){
        val zoomLevel = 15.0f
        mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoomLevel))
    }
}