package com.aiko.teste.sptrans

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.compose.rememberNavController
import com.aiko.teste.sptrans.ui.theme.SPTransTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val tag = MainActivity::class.toString()

    private var isPermissionsRunning = true
    private var isAuthenticationRunning = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                isPermissionsRunning || isAuthenticationRunning
            }
        }
        setContent {
            SPTransTheme {
                SetUpApp()
            }
        }
    }

    @Composable
    private fun SetUpApp(viewModel: MainViewModel = hiltViewModel<MainViewModel>()) {
        val navController = rememberNavController()
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root
        )
        RequestUserPermissions(viewModel)

        val authResultObserver = Observer<Result<Boolean>> { authResult ->
            if (authResult.isSuccess) {
                Log.i(tag, "isSuccess = ${authResult.getOrNull().toString()}")
                isAuthenticationRunning = false
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.api_error_authentication_message),
                    Toast.LENGTH_LONG
                ).show()
                Log.e(tag, "isFailure = ${authResult.exceptionOrNull().toString()}")
                finish()
            }
        }
        viewModel.authenticationResult.observe(this, authResultObserver)
        viewModel.authenticateApi()
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun RequestUserPermissions(viewModel: MainViewModel) {
        val permissionState = rememberMultiplePermissionsState(
            permissions = listOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.INTERNET
            )
        )
        LaunchedEffect(Unit) {
            permissionState.launchMultiplePermissionRequest()
        }
        when {
            else -> {
                isPermissionsRunning = false
                viewModel.savePermissionResult(permissionState.allPermissionsGranted)
            }
        }
    }
}