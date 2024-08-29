package br.com.aiko.projetoolhovivo.di.position

import androidx.lifecycle.ViewModel
import br.com.aiko.projetoolhovivo.di.ViewModelKey
import br.com.aiko.projetoolhovivo.ui.position.PositionMapViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PositionMapViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(PositionMapViewModel::class)
    abstract fun bindPositionMapViewModel(viewModel: PositionMapViewModel) : ViewModel
}