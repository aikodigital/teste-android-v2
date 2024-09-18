package br.com.aikosptrans.domain.usecase

import br.com.aikosptrans.domain.entity.BusStop

fun interface GetBusStopByLineUseCase : suspend (String) -> List<BusStop>