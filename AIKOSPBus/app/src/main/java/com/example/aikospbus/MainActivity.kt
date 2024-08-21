package com.example.aikospbus

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.aikospbus.custom_components.MenuUtil
import com.example.aikospbus.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mMap: GoogleMap

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val navController = findNavController(R.id.nav_host_fragment_content_main)

        setMenuActions()

        binding.menuLayoutItem.menuLayout.setOnClickListener {
            binding.menuLayoutItem.menuLayout.getFocusedRect(Rect())
        }

        binding.menuIcon.setOnClickListener {
            MenuUtil().showMenu(binding.menuLayoutItem.root)
            hideSoftKeyboard()
        }

        binding.searchIcon.setOnClickListener {
            MenuUtil().hideMenu(binding.menuLayoutItem.root)
        }

        binding.searchIcon.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                hideSoftKeyboard()
            } else {
                MenuUtil().hideMenu(binding.menuLayoutItem.root)
            }
        }

        binding.mainLayout.setOnClickListener {
            hideSoftKeyboard()
        }

        binding.mainLayout.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val rect = Rect()
                binding.menuLayoutItem.root.getGlobalVisibleRect(rect)
                if (!rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    MenuUtil().hideMenu(binding.menuLayoutItem.root)
                }
            }
            false
        }
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = this.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun setMenuActions() {
        with(binding.menuLayoutItem) {
            val positionLayout = position
            val linesLayout = lines
            val stopsLayout = stops
            val positionImage = positionEyeIcon
            val linesImage = linesEyeIcon
            val stopsImage = stopsEyeIcon

            MenuUtil().setClickListener(positionLayout, positionImage)
            MenuUtil().setClickListener(linesLayout, linesImage)
            MenuUtil().setClickListener(stopsLayout, stopsImage)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sp = LatLng(-23.5489, -46.6388)
        mMap.addMarker(MarkerOptions().position(sp).title("Marker in São Paulo"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sp))
        val cameraUpdate = CameraUpdateFactory.zoomTo(10f)
        mMap.moveCamera(cameraUpdate)

        googleMap.setOnMapClickListener {
            hideSoftKeyboard()
            MenuUtil().hideMenu(binding.menuLayoutItem.root)
        }
    }
}