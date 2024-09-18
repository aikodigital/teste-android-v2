package br.com.aikosptrans.di

import br.com.aikosptrans.domain.repository.DataRepository
import br.com.aikosptrans.domain.usecase.AuthenticateUserUseCase
import br.com.aikosptrans.domain.usecase.GetBusLineUseCase
import br.com.aikosptrans.domain.usecase.GetBusLocationUseCase
import br.com.aikosptrans.domain.usecase.GetBusStopUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

internal object UseCaseModule : KoinModule {
    override val module: Module = module {
        single { AuthenticateUserUseCase(get<DataRepository>()::authenticate) }
        single { GetBusLocationUseCase(get<DataRepository>()::getBusesLocation) }
        single { GetBusStopUseCase(get<DataRepository>()::getBusStop) }
        single { GetBusLineUseCase(get<DataRepository>()::getBusLineDetail) }
    }
}