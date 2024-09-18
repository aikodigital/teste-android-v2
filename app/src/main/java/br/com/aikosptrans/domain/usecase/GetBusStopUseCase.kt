package br.com.aikosptrans.domain.usecase

import br.com.aikosptrans.domain.entity.BusStop

fun interface GetBusStopUseCase : suspend (String) -> List<BusStop>