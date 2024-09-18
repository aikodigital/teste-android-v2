package br.com.aikosptrans.di

import br.com.aikosptrans.presentation.busline.viewmodel.BusLineViewModel
import br.com.aikosptrans.presentation.busmap.viewmodel.BusMapViewModel
import br.com.aikosptrans.presentation.busstopmap.viewmodel.BusStopMapViewModel
import br.com.aikosptrans.presentation.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

internal object ViewModelModule : KoinModule {
    override val module: Module = module {
        viewModelOf(::SplashViewModel)
        viewModelOf(::BusMapViewModel)
        viewModelOf(::BusStopMapViewModel)
        viewModelOf(::BusLineViewModel)
    }
}