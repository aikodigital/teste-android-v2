package hopeapps.dedev.sptrans.di

import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import hopeapps.dedev.sptrans.data.repository.SearchRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val repositoryModule = module {
    singleOf(::SearchRepositoryImpl).bind<SearchRepository>()
}