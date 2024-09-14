package br.com.aikosptrans.di

import br.com.aikosptrans.data.remote.datasource.LoginDataSource
import br.com.aikosptrans.data.remote.datasource.LoginDataSourceImpl
import br.com.aikosptrans.data.remote.repository.LoginRepositoryImpl
import br.com.aikosptrans.domain.repository.LoginRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal object DataModule : KoinModule {
    override val module: Module = module {
        //DataSources
        singleOf(::LoginDataSourceImpl) bind LoginDataSource::class

        //Repositories
        singleOf(::LoginRepositoryImpl) bind LoginRepository::class
    }
}