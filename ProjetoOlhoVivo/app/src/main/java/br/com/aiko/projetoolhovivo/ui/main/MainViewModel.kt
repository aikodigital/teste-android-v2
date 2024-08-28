package br.com.aiko.projetoolhovivo.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aiko.projetoolhovivo.domain.usecase.auth.AuthUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    var token: String = ""

    private val _error = MutableLiveData<String?>(null)

    val error: LiveData<String?> get() = _error

    fun auth() = viewModelScope.launch {
        authUseCase.invoke(token).onSuccess {
        }.onFailure { errorResponse ->
            Log.e("FAIL", errorResponse.message.toString())
            _error.postValue(errorResponse.message)
        }
    }
}