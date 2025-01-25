package br.com.danilo.aikotestebus.di

import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.data.repository.BusRepositoryImpl
import br.com.danilo.aikotestebus.domain.usecase.LineBusDetailUseCase
import br.com.danilo.aikotestebus.domain.usecase.AuthenticatorUseCase
import br.com.danilo.aikotestebus.domain.usecase.MapLocationBusUseCase
import br.com.danilo.aikotestebus.presentation.features.lines.LineBusDetailsViewModel
import br.com.danilo.aikotestebus.presentation.features.authenticator.AuthenticatorViewModel
import br.com.danilo.aikotestebus.presentation.features.maplocation.MapLocationBusViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object BusDI {
    val module = module {
        factory<IBusRepository> { BusRepositoryImpl(get())}
        single { LineBusDetailUseCase(get()) }
        single { AuthenticatorUseCase(get()) }
        single { MapLocationBusUseCase(get()) }
        viewModel { LineBusDetailsViewModel(get()) }
        viewModel { MapLocationBusViewModel(get()) }
        viewModel { AuthenticatorViewModel(get()) }
    }
}