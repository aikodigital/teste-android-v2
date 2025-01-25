package br.com.danilo.aikotestebus.di

import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.data.repository.IBusRepositoryImpl
import br.com.danilo.aikotestebus.domain.usecase.LineBusDetailUseCase
import br.com.danilo.aikotestebus.domain.usecase.SplashUseCase
import br.com.danilo.aikotestebus.presentation.features.lines.LineBusDetailsViewModel
import br.com.danilo.aikotestebus.presentation.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object BusDI {
    val module = module {
        factory<IBusRepository> { IBusRepositoryImpl(get())}
        single { LineBusDetailUseCase(get()) }
        single { SplashUseCase(get()) }
        viewModel { LineBusDetailsViewModel(get()) }
        viewModel { SplashViewModel(get()) }
    }
}