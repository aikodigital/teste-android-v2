package com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.AuthenticateUseCase

class HomeViewModelFactory(
    private val application: Application,
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            application,
            authenticateUseCase
        ) as T
    }

}