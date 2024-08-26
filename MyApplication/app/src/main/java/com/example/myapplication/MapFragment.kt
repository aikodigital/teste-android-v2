package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment(), OnMapReadyCallback {

    private val ruas = listOf(
        "Avenida Paulista",
        "Rua Oscar Freire",
        "Avenida Ibirapuera",
        "Rua 25 de Março"
    )

    private var ruaIndex = 0
    private var charIndex = 0
    private lateinit var googleMap: GoogleMap
    private lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        editText = view.findViewById(R.id.editTextText)

        // Inicialize o mapa
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment1) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        // Configurar o Runnable para o EditText
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

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val avenidaPaulista = LatLng(-23.5615, -46.6559)
        googleMap.addMarker(
            MarkerOptions().position(avenidaPaulista).title("Avenida Paulista")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(avenidaPaulista, 15f))
    }
}