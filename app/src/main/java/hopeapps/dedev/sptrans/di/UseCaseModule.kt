package hopeapps.dedev.sptrans.di

import hopeapps.dedev.sptrans.domain.usecase.AuthUseCase
import hopeapps.dedev.sptrans.domain.usecase.BusStopUseCase
import hopeapps.dedev.sptrans.domain.usecase.BusLineUseCase
import hopeapps.dedev.sptrans.domain.usecase.BusStopByIdLineUseCase
import org.koin.dsl.module


val useCaseModule = module {
    single { BusLineUseCase(get()) }
    single { AuthUseCase(get()) }
    single { BusStopUseCase(get()) }
    single { BusStopByIdLineUseCase(get()) }
}
