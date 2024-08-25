package com.cesarsoftdevelopment.aikopublictransport.ui.home.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cesarsoftdevelopment.aikopublictransport.R
import com.cesarsoftdevelopment.aikopublictransport.databinding.ActivityHomeBinding
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.HomeViewModel
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.HomeViewModelFactory
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var navHostFragment: NavHostFragment

    private lateinit var navController: NavController

    @Inject
    lateinit var factory : HomeViewModelFactory

    private val viewModel : HomeViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpSplashScreen()
        setUpViews()
        observeInternetConnection()
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


    private fun observeInternetConnection() {
        viewModel.isInternetAvailable.observe(this, Observer { response ->

            when(response) {

                is Resource.Success -> {
                    response.data?.let {
                        observeAuthentication()
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        navController.navigate(R.id.noInternetFragment)
                    }

                }

                else -> {}
            }
        })
    }
    private fun observeAuthentication() {

        viewModel.authenticate()

        viewModel.authenticated.observe(this, Observer { response ->

            when(response) {

                is Resource.Success -> {
                    response.data?.let {
                        navController.navigate(R.id.searchHistoricFragment)
                    }
                }

                is Resource.Error -> {
                    response.message?.let {
                        Toast.makeText(
                            this,
                            "Usuário não autenticado",
                            Toast.LENGTH_SHORT

                        ).show()
                    }
                    finish()

                }

                else -> {}
            }
        })
    }


    private fun setUpNavigationBar() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.searchHistoricFragment -> {
                    navController.navigate(R.id.searchHistoricFragment)
                    true
                }

                R.id.busLinesFragment -> {
                    navController.navigate(R.id.busLinesFragment)
                    true
                }

                R.id.mapsFragment -> {
                    navController.navigate(R.id.mapsFragment)
                    true
                }

                else -> false
            }
        }
    }

}