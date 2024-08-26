package com.aiko.teste.sptrans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiko.teste.sptrans.data.APIService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: APIService,
    @Named("apiToken") private val apiToken: String
) : ViewModel() {

    private val _authenticationResult = MutableLiveData<Result<Boolean>>()
    val authenticationResult: LiveData<Result<Boolean>> = _authenticationResult

    private var permissionFinished = false
    private var authenticationFinished = false

    private val _setUpFinished = MutableLiveData(false)
    val setUpFinished: LiveData<Boolean> = _setUpFinished

    fun authenticateApi() {
        val call = apiService.authenticate(apiToken)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        _authenticationResult.value = Result.success(it)
                    } ?: run {
                        _authenticationResult.value =
                            Result.failure(Exception("Empty response body"))
                    }
                } else {
                    _authenticationResult.value =
                        Result.failure(Exception("API error: ${response.code()}"))
                }

                authenticationFinished = true
                checkSetUpFinished()
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                _authenticationResult.value = Result.failure(t)
                authenticationFinished = true
                checkSetUpFinished()
            }
        })
    }

    fun permissionFinished() {
        permissionFinished = true
        checkSetUpFinished()
    }

    private fun checkSetUpFinished() {
        if (permissionFinished && authenticationFinished) _setUpFinished.value = true
    }
}