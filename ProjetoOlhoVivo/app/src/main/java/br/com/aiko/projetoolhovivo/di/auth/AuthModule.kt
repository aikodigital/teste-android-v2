package br.com.aiko.projetoolhovivo.di.auth

import br.com.aiko.projetoolhovivo.data.service.auth.AuthRepository
import br.com.aiko.projetoolhovivo.domain.usecase.auth.AuthUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {
    @Provides
    fun providesAuthRepository(retrofit: Retrofit): AuthRepository =
        AuthRepository(retrofit)

    @Provides
    fun providesAuthUseCase(authRepository: AuthRepository): AuthUseCase = AuthUseCase(
        repository = authRepository
    )
}