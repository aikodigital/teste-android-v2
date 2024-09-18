package br.com.aikosptrans.domain.usecase

import br.com.aikosptrans.domain.entity.Bus

fun interface GetBusLocationUseCase: suspend () -> List<Bus>