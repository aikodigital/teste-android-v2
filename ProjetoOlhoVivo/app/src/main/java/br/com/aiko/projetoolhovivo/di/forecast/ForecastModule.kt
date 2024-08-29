package br.com.aiko.projetoolhovivo.di.forecast

import br.com.aiko.projetoolhovivo.data.service.forecast.ForecastRepository
import br.com.aiko.projetoolhovivo.domain.usecase.forecast.ForecastUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ForecastModule {
    @Provides
    fun providesForecastRepository(retrofit: Retrofit): ForecastRepository =
        ForecastRepository(retrofit)

    @Provides
    fun providesForecastUseCase(forecastRepository: ForecastRepository):
            ForecastUseCase = ForecastUseCase(repository = forecastRepository)
}