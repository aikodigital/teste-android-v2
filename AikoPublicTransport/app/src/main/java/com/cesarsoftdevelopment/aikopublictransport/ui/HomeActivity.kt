package com.cesarsoftdevelopment.aikopublictransport.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
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

            val slideUp = ObjectAnimator.ofFloat(
                splashScreenView,
                "translationY",
                0f, -splashScreenView.height.toFloat())
            slideUp.duration = 500L

            val fadeOut = ObjectAnimator.ofFloat(
                splashScreenView,
                "alpha",
                1f, 0f
            )

            fadeOut.duration = 500L

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(slideUp, fadeOut)
            animatorSet.start()

            animatorSet.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    splashScreenViewProvider.remove()
                }
            })
        }
    }
}