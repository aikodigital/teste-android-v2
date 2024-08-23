package com.cesarsoftdevelopment.aikopublictransport.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cesarsoftdevelopment.aikopublictransport.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpSplashScreen()
        setContentView(R.layout.activity_home)
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


}