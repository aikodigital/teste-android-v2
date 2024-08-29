package br.com.aiko.projetoolhovivo.shared

import android.app.Application
import br.com.aiko.projetoolhovivo.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class AppOlhoVivoApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}