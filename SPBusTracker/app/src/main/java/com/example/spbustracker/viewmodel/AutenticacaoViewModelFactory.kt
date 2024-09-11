package com.example.spbustracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spbustracker.repository.AutenticacaoRepository

class AutenticacaoViewModelFactory(
    private val repository: AutenticacaoRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AutenticacaoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AutenticacaoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
