package br.com.aikosptrans.domain.usecase

fun interface GetArriveForecastTimeUseCase : suspend (String,String) -> List<String>