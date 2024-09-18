package br.com.aikosptrans.data.remote.repository

import br.com.aikosptrans.data.remote.datasource.RemoteDataSource
import br.com.aikosptrans.data.remote.mapper.mapToArriveForecastTime
import br.com.aikosptrans.data.remote.mapper.mapToBusLine
import br.com.aikosptrans.data.remote.mapper.mapToBusStop
import br.com.aikosptrans.data.remote.mapper.mapToBuses
import br.com.aikosptrans.domain.entity.Bus
import br.com.aikosptrans.domain.entity.BusLine
import br.com.aikosptrans.domain.entity.BusStop
import br.com.aikosptrans.domain.repository.DataRepository

class DataRepositoryImpl(
    private val dataSource: RemoteDataSource
) : DataRepository {

    override suspend fun authenticate(): Boolean = dataSource.authenticate()

    override suspend fun getBusesLocation(): List<Bus> {
        return dataSource
            .getBusesLocation()
            .busLine
            .mapToBuses()
    }

    override suspend fun getBusStop(query: String): List<BusStop> {
        return dataSource
            .getBusStop(query)
            .mapToBusStop()
    }

    override suspend fun getBusLineDetail(query: String): List<BusLine> {
        return dataSource
            .getBusLineDetail(query)
            .mapToBusLine()
    }

    override suspend fun getBusStopByLine(idLine: String): List<BusStop> {
        return dataSource
            .getBusStopByLine(idLine)
            .mapToBusStop()
    }

    override suspend fun getArriveForecastTime(
        idLine: String,
        idStop: String
    ): List<String> {
        return dataSource
            .getArrivalForecastTime(
                idLine = idLine,
                idStop = idStop
            ).mapToArriveForecastTime()
    }
}