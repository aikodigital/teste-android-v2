package br.com.aikosptrans.di

import br.com.aikosptrans.domain.repository.LoginRepository
import br.com.aikosptrans.domain.usecase.AuthenticateUserUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

internal object UseCaseModule : KoinModule {
    override val module: Module = module {
        single { AuthenticateUserUseCase(get<LoginRepository>()::authenticate) }
    }
}