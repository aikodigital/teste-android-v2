package br.com.aiko.projetoolhovivo.di.forecast

import androidx.lifecycle.ViewModel
import br.com.aiko.projetoolhovivo.di.ViewModelKey
import br.com.aiko.projetoolhovivo.ui.forecast.ForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ForecastViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    abstract fun bindForecastViewModel(viewModel: ForecastViewModel) : ViewModel
}