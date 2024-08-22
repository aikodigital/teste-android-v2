package com.aiko.teste.sptrans

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val tag = MainActivity::class.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetUpApp()
        }
    }

    @Composable
    private fun SetUpApp(viewModel: MainViewModel = hiltViewModel<MainViewModel>()) {
        RequestUserPermissions(viewModel)
        authenticateApi(viewModel)

        val setUpFinished by viewModel.setUpFinished.observeAsState()
        when (setUpFinished) {
            true -> SetUpNavigation()
            else -> return
        }
    }

    @Composable
    fun SetUpNavigation() {
        val navController = rememberNavController()
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root
        )
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun RequestUserPermissions(viewModel: MainViewModel) {
        val permissionState =
            rememberPermissionState(android.Manifest.permission.ACCESS_COARSE_LOCATION)

        val requestPermissionLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            Log.d(tag, "permission is granted = $isGranted")
            viewModel.permissionFinished()
        }

        LaunchedEffect(permissionState) {
            Log.d(tag, "permissionState = ${permissionState.status}")
            if (!permissionState.status.isGranted) {
                requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            } else {
                viewModel.permissionFinished()
            }
        }
    }

    private fun authenticateApi(viewModel: MainViewModel) {
        val authResultObserver = Observer<Result<Boolean>> { authResult ->
            if (authResult.isSuccess) {
                Log.d(tag, "isSuccess = ${authResult.getOrNull().toString()}")
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.api_error_authentication_message),
                    Toast.LENGTH_LONG
                ).show()
                Log.d(tag, "isFailure = ${authResult.exceptionOrNull().toString()}")
                finish()
            }
        }
        viewModel.authenticationResult.observe(this, authResultObserver)
        viewModel.authenticateApi()
    }
}