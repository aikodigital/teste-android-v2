package com.cesarsoftdevelopment.aikopublictransport.di.modules

import android.app.Application
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.AuthenticateUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetBusLinesUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetStopsByLineUseCase
import com.cesarsoftdevelopment.aikopublictransport.domain.usecase.GetVehiclesPositionByLineUseCase
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.BusLinesViewModelFactory
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.HomeViewModelFactory
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.MapViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideHomeViewModelFactory(
        application: Application,
        authenticateUseCase: AuthenticateUseCase

    ) : HomeViewModelFactory {
        return HomeViewModelFactory(
            application,
            authenticateUseCase

        )
    }

    @Singleton
    @Provides
    fun provideBusLinesViewModelFactory(
        getBusLinesUseCase: GetBusLinesUseCase,
        getVehiclesPositionByLineUseCase: GetVehiclesPositionByLineUseCase,
        getStopsByLineUseCase: GetStopsByLineUseCase

    ) : BusLinesViewModelFactory {
        return BusLinesViewModelFactory(
            getBusLinesUseCase,
            getVehiclesPositionByLineUseCase,
            getStopsByLineUseCase

        )
    }

    @Singleton
    @Provides
    fun provideMapViewModelFactory(application: Application) : MapViewModelFactory {
        return MapViewModelFactory(
            application
        )
    }

}