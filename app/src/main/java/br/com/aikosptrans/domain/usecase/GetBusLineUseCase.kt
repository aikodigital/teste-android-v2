package br.com.aikosptrans.domain.usecase

import br.com.aikosptrans.domain.entity.BusLine

fun interface GetBusLineUseCase: suspend (String) -> List<BusLine>