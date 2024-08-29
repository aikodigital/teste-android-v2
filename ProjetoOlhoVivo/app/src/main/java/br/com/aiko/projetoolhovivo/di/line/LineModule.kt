package br.com.aiko.projetoolhovivo.di.line

import br.com.aiko.projetoolhovivo.data.service.line.LineRepository
import br.com.aiko.projetoolhovivo.domain.usecase.line.LineUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class LineModule {
    @Provides
    fun providesLineRepository(retrofit: Retrofit): LineRepository =
        LineRepository(retrofit)

    @Provides
    fun providesBusLineUseCase(lineRepository: LineRepository):
            LineUseCase = LineUseCase(repository = lineRepository)
}