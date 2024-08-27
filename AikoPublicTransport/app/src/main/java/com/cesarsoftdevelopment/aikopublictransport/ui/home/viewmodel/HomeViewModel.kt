package com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.AuthenticateUseCase
import com.cesarsoftdevelopment.aikopublictransport.utils.AppStrings
import com.cesarsoftdevelopment.aikopublictransport.utils.NetworkUtils
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val application: Application,
    private val authenticateUseCase: AuthenticateUseCase
) : AndroidViewModel(application) {

    private val _authenticated : MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val authenticated : LiveData<Resource<Boolean>> = _authenticated

    private val _isInternetAvailable : MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val isInternetAvailable : LiveData<Resource<Boolean>> = _isInternetAvailable

    init {
        checkInternetConnection()
    }

    private fun checkInternetConnection() {

        try {
            if(NetworkUtils.isNetworkAvailable(application)) {
                _isInternetAvailable.postValue(Resource.Success(true))
            }else {
                _isInternetAvailable.postValue(Resource.Error("Internet is not available"))
            }

        }catch (err : Exception) {
            _isInternetAvailable.postValue(Resource.Error(err.message.toString()))
        }

    }

    fun authenticate() {

        viewModelScope.launch {

            _authenticated.postValue(Resource.Loading())

            try {
                val response = authenticateUseCase.invoke(AppStrings.TOKEN)
                _authenticated.postValue(response)

            }catch (err : Exception) {
                _authenticated.postValue(Resource.Error(err.message.toString()))
            }

        }

    }

}