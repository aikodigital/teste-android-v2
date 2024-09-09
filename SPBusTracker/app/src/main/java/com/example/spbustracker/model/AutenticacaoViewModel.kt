package com.example.spbustracker.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spbustracker.repository.AutenticacaoRepository
import kotlinx.coroutines.launch

class AutenticacaoViewModel(private val repository: AutenticacaoRepository) : ViewModel() {

    val autenticado = MutableLiveData<Boolean>()

    fun autenticar(token: String) {
        viewModelScope.launch {
            val sucesso = repository.autenticar(token)
            autenticado.postValue(sucesso)
        }
    }
}

