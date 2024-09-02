package com.example.testeaiko

import android.util.Log
import com.example.testeaiko.services.TransitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(private val apiService: TransitApiService) {

    private var isAuthenticated = false

    init {
        authenticate()
    }

    private fun authenticate() {
        if (isAuthenticated) return  // Prevent multiple authentications

        val call = apiService.createApi().authenticate(BuildConfig.TRANSIT_API_KEY)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
            ) {
                if (response.isSuccessful) {
                    isAuthenticated = true
                } else {
                    // Handle unsuccessful authentication
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                // Handle network failure or other errors
            }
        })
    }

    private fun resetAuthentication() {
        isAuthenticated = false
    }

    fun handleAuthenticationFailure() {
        // Handle authentication failure
        Log.d("SessionManager", "Unauthorized request, re-authenticating...")
        resetAuthentication()
        authenticate()
    }

}