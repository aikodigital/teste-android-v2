package br.com.aiko.projetoolhovivo.di.stop

import br.com.aiko.projetoolhovivo.data.service.stop.StopRepository
import br.com.aiko.projetoolhovivo.domain.usecase.stop.StopUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class StopModule {
    @Provides
    fun providesStopRepository(retrofit: Retrofit): StopRepository =
        StopRepository(retrofit)

    @Provides
    fun providesStopUseCase(stopRepository: StopRepository):
            StopUseCase = StopUseCase(repository = stopRepository)
}