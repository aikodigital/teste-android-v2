package br.com.aiko.projetoolhovivo.di.main

import androidx.lifecycle.ViewModel
import br.com.aiko.projetoolhovivo.di.ViewModelKey
import br.com.aiko.projetoolhovivo.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel) : ViewModel
}