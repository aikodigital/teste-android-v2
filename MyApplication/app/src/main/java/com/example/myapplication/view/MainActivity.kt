package com.example.myapplication.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.infra.Retrofit
import com.example.myapplication.infra.Token
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mapFragment by lazy { MapFragment() }
    private val lineFragment by lazy { LineFragment() }
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar = findViewById(R.id.progress_bar)

        authenticateUser()
        setupBottomNavigation()
        loadDefaultFragment()
    }

    private fun authenticateUser() {
        CoroutineScope(Dispatchers.IO).launch {
            Retrofit().apiService.auth(Token.valueApi.TOKEN)
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.lines -> lineFragment
                R.id.busStop -> mapFragment
                else -> null
            }
            fragment?.let { changeFragment(it) }
            fragment != null
        }
    }

    private fun loadDefaultFragment() {
        changeFragment(lineFragment)
    }

    private fun changeFragment(fragment: Fragment) {
        progressBar.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, fragment)
            .commit()

        Handler(Looper.getMainLooper()).postDelayed({
            progressBar.visibility = View.GONE
        }, 500)
    }
}

