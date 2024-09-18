package br.com.aikosptrans.di

import br.com.aikosptrans.data.remote.datasource.RemoteDataSource
import br.com.aikosptrans.data.remote.datasource.RemoteDataSourceImpl
import br.com.aikosptrans.data.remote.repository.DataRepositoryImpl
import br.com.aikosptrans.domain.repository.DataRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal object DataModule : KoinModule {
    override val module: Module = module {
        //DataSources
        singleOf(::RemoteDataSourceImpl) bind RemoteDataSource::class

        //Repositories
        singleOf(::DataRepositoryImpl) bind DataRepository::class
    }
}