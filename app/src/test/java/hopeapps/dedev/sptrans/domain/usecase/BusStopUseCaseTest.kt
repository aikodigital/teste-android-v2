package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.data.model.BusStop
import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BusStopUseCaseTest {
    private lateinit var repository: SearchRepository
    private lateinit var busStopUseCase: BusStopUseCase
    private var mockBusStop = BusStop(1,"", "", 0.0, 0.0)

    @Before
    fun setup() {
        repository = mockk()
        busStopUseCase = BusStopUseCase(repository)
    }

    @Test
    fun `invoke should return success when bus stops are found`() = runBlocking {
        val busStops = listOf(mockBusStop)
        coEvery { repository.searchBusStop("any") } returns ApiResponse.Success(busStops)
        val result = busStopUseCase("any")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == busStops)
    }

    @Test
    fun `invoke should return NotFoundException when API responds with 404 error`() = runBlocking {
        coEvery { repository.searchBusStop("Unknown") } returns ApiResponse.Error(404)
        val result = busStopUseCase("Unknown")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.NotFoundException)
    }

    @Test
    fun `invoke should return ServerException when API responds with 500 error`() = runBlocking {
        coEvery { repository.searchBusStop("Error") } returns ApiResponse.Error(statusCode = 500)
        val result = busStopUseCase("Error")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.ServerException)
    }

    @Test
    fun `invoke should return UnknownException when an unexpected error occurs`() = runBlocking {
        coEvery { repository.searchBusStop("Crash") } throws RuntimeException()
        val result = busStopUseCase("Crash")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.UnknownException)
    }
}