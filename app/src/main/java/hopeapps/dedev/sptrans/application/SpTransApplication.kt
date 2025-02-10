package hopeapps.dedev.sptrans.application

import android.app.Application
import hopeapps.dedev.sptrans.di.NetworkModule
import hopeapps.dedev.sptrans.di.repositoryModule
import hopeapps.dedev.sptrans.di.useCaseModule
import hopeapps.dedev.sptrans.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SpTransApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SpTransApplication)
            modules(
                repositoryModule,
                viewModelModule,
                useCaseModule,
                NetworkModule.module,
            )
        }
    }
}