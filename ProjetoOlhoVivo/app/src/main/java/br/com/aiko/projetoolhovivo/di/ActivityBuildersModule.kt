package br.com.aiko.projetoolhovivo.di

import br.com.aiko.projetoolhovivo.di.auth.AuthModule
import br.com.aiko.projetoolhovivo.di.forecast.ForecastModule
import br.com.aiko.projetoolhovivo.di.forecast.ForecastViewModelModule
import br.com.aiko.projetoolhovivo.di.line.LineModule
import br.com.aiko.projetoolhovivo.di.line.LineViewModelModule
import br.com.aiko.projetoolhovivo.di.main.MainFragmentBuildersModule
import br.com.aiko.projetoolhovivo.di.main.MainViewModelModule
import br.com.aiko.projetoolhovivo.di.position.PositionMapViewModelModule
import br.com.aiko.projetoolhovivo.di.position.PositionModule
import br.com.aiko.projetoolhovivo.di.stop.StopModule
import br.com.aiko.projetoolhovivo.ui.forecast.view.ForecastDetailsActivity
import br.com.aiko.projetoolhovivo.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class,
            AuthModule::class,
            LineModule::class,
            PositionModule::class,
            StopModule::class,
            MainViewModelModule::class,
            LineViewModelModule::class,
            PositionMapViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(
        modules = [
            ForecastModule::class,
            ForecastViewModelModule::class
        ]
    )
    abstract fun contributeForecastDetailsActivity(): ForecastDetailsActivity
}