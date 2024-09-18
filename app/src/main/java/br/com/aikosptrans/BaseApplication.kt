package br.com.aikosptrans

import android.app.Application
import br.com.aikosptrans.di.DataModule
import br.com.aikosptrans.di.NetworkModule
import br.com.aikosptrans.di.UseCaseModule
import br.com.aikosptrans.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                NetworkModule.module,
                DataModule.module,
                UseCaseModule.module,
                ViewModelModule.module
            )
        }
        super.onCreate()
    }
}