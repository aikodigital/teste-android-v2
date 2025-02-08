package hopeapps.dedev.sptrans.di

import hopeapps.dedev.sptrans.presentation.bus_stop.BusStopViewModel
import hopeapps.dedev.sptrans.presentation.line_bus.LineBusViewModel
import hopeapps.dedev.sptrans.presentation.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::LineBusViewModel)
    viewModelOf(::BusStopViewModel)
    viewModelOf(::BusStopViewModel)
}