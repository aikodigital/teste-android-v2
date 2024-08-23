package com.matreis.teste.sptrans.helper

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class Dispatcher(val dispatcher: CoroutineDispatcherType)

enum class CoroutineDispatcherType {
    IO, MAIN, DEFAULT
}