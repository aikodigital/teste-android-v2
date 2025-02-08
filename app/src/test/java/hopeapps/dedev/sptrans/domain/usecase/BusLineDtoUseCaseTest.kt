package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.data.models.BusLineDto
import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class BusLineDtoUseCaseTest {

    private lateinit var repository: SearchRepository
    private lateinit var busLineUseCase: BusLineUseCase

    private var mockBusLine = BusLine(
        null,
        false,
        "",
        1,
        1,
        mainTerminal = "",
        secondaryTerminal = ""
    )

    @Before
    fun setup() {
        repository = mockk()
        busLineUseCase = BusLineUseCase(repository)
    }


    @Test
    fun `invoke should return success when bus lines are found`() = runBlocking {
        val busLines = listOf(mockBusLine)
        coEvery { repository.searchBusLines("any") } returns ApiResponse.Success(busLines)
        val result = busLineUseCase("any")
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull() == busLines)
    }

    @Test
    fun `invoke should return NotFoundException when API responds with 404 error`() = runBlocking {
        coEvery { repository.searchBusLines("Unknown") } returns ApiResponse.Error(404)
        val result = busLineUseCase("Unknown")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.NotFoundException)
    }

    @Test
    fun `invoke should return ServerException when API responds with 500 error`() = runBlocking {
        coEvery { repository.searchBusLines("Error") } returns ApiResponse.Error(500)
        val result = busLineUseCase("Error")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.ServerException)
    }

    @Test
    fun `invoke should return UnknownException when an unexpected error occurs`() = runBlocking {
        coEvery { repository.searchBusLines("Crash") } throws RuntimeException()
        val result = busLineUseCase("any")
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.UnknownException)
    }
}