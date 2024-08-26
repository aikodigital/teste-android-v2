package com.cesarsoftdevelopment.aikopublictransport.di.modules

import com.cesarsoftdevelopment.aikopublictransport.ui.home.adapters.BusLinesAdapter
import com.cesarsoftdevelopment.aikopublictransport.ui.home.viewmodel.BusLinesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideNewsAdapter(busLinesViewModel: BusLinesViewModel) : BusLinesAdapter {
        return BusLinesAdapter(busLinesViewModel)
    }

}



