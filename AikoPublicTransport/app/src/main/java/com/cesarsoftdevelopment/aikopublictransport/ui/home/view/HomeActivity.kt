package com.cesarsoftdevelopment.aikopublictransport.ui.home.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cesarsoftdevelopment.aikopublictransport.R
import com.cesarsoftdevelopment.aikopublictransport.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpSplashScreen()
        setUpViews()
        setUpNavigationBar()
    }

    private fun setUpViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setUpSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashScreenView = splashScreenViewProvider.view
            val fadeOut = ObjectAnimator.ofFloat(
                splashScreenView,
                "alpha",
                1f, 0f
            ).apply {
                duration = 500L
            }
            fadeOut.start()
            fadeOut.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    splashScreenViewProvider.remove()
                }
            })
        }
    }

    private fun setUpNavigationBar() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapsFragment -> {
                    navController.navigate(R.id.mapsFragment)
                    true
                }
                R.id.busLinesFragment -> {
                    navController.navigate(R.id.busLinesFragment)
                    true
                }
                else -> false
            }
        }
    }


}