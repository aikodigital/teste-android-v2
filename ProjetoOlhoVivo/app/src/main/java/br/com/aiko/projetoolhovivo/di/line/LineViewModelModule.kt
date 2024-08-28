package br.com.aiko.projetoolhovivo.di.line

import androidx.lifecycle.ViewModel
import br.com.aiko.projetoolhovivo.di.ViewModelKey
import br.com.aiko.projetoolhovivo.ui.line.LineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LineViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LineViewModel::class)
    abstract fun bindLineViewModel(viewModel: LineViewModel) : ViewModel
}