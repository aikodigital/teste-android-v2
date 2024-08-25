package com.cesarsoftdevelopment.aikopublictransport.di.modules

import com.cesarsoftdevelopment.aikopublictransport.ui.home.adapters.BusLinesAdapter
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
    fun provideNewsAdapter() : BusLinesAdapter {
        return BusLinesAdapter()
    }

}



