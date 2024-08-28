package br.com.aiko.projetoolhovivo.di.position

import br.com.aiko.projetoolhovivo.data.service.position.PositionRepository
import br.com.aiko.projetoolhovivo.domain.usecase.position.PositionUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PositionModule {
    @Provides
    fun providesPositionRepository(retrofit: Retrofit): PositionRepository =
        PositionRepository(retrofit)

    @Provides
    fun providesBusLineUseCase(positionRepository: PositionRepository):
            PositionUseCase = PositionUseCase(repository = positionRepository)
}