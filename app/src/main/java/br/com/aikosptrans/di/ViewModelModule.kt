package br.com.aikosptrans.di

import br.com.aikosptrans.presentation.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

internal object ViewModelModule : KoinModule {
    override val module: Module = module {
        viewModelOf(::SplashViewModel)
    }
}