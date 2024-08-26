package com.example.myapplication

import BussFragment
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNavigation)
        progressBar = findViewById(R.id.progress_bar)

        if (savedInstanceState == null) {
            changeFragment(BussFragment())
        }

        navOptions()
    }

    private fun navOptions() {
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.lines -> {
                    changeFragment(LineFragment())
                    true
                }
                R.id.busStop -> {
                    changeFragment(MapFragment())
                    true
                }
                R.id.bus -> {
                    changeFragment(BussFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        progressBar.visibility = View.VISIBLE

        supportFragmentManager.beginTransaction()
            .replace(R.id.map_container, fragment)
            .addToBackStack(null)
            .commit()

        // Adiciona um listener para quando a transação for completada
        supportFragmentManager.addOnBackStackChangedListener {
            progressBar.visibility = View.GONE
        }
    }
}