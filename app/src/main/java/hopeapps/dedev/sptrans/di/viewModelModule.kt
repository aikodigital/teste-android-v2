package hopeapps.dedev.sptrans.di

import hopeapps.dedev.sptrans.presentation.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SearchViewModel)
}