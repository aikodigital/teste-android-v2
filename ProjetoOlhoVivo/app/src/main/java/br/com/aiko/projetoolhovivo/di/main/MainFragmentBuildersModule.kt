package br.com.aiko.projetoolhovivo.di.main

import br.com.aiko.projetoolhovivo.ui.line.view.ListLineFragment
import br.com.aiko.projetoolhovivo.ui.position.view.MapPositionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMapFragmentFactoryFragmentBuildersModule() : MapPositionFragment

    @ContributesAndroidInjector
    abstract fun contributeListLineFragmentBuildersModule() : ListLineFragment
}