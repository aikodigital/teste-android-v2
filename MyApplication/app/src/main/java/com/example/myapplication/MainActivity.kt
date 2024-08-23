package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private val ruas = listOf(
        "Avenida Paulista",
        "Rua Oscar Freire",
        "Avenida Ibirapuera",
        "Rua 25 de Março"
    )

    private var ruaIndex = 0
    private var charIndex = 0
    private lateinit var placesClient: PlacesClient
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editTextText)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "YOUR_API_KEY")
        }
        placesClient = Places.createClient(this)

        mapFragment.getMapAsync(this)

        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val currentRua = ruas[ruaIndex]

                // Escreve a rua letra por letra
                if (charIndex < currentRua.length) {
                    editText.hint = currentRua.substring(0, charIndex + 1)
                    charIndex++
                    handler.postDelayed(this, 150)
                } else {
                    charIndex = 0
                    ruaIndex = (ruaIndex + 1) % ruas.size
                    handler.postDelayed(this, 1000)
                }
            }
        }
        handler.post(runnable)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val avenidaPaulista = LatLng(-23.5615, -46.6559) // Exemplo de coordenadas
        googleMap.addMarker(MarkerOptions().position(avenidaPaulista).title("Avenida Paulista"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(avenidaPaulista, 15f))
    }
}