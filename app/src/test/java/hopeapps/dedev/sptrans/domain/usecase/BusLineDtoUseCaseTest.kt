package hopeapps.dedev.sptrans.domain.usecase


import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class BusLineUseCaseTest {

    private lateinit var repository: SearchRepository
    private lateinit var busLineUseCase: BusLineUseCase

    private val mockBusLine = BusLine(
        null,
        false,
        "",
        1,
        1,
        "",
        ""
    )

    @Before
    fun setup() {
        repository = mockk()
        busLineUseCase = BusLineUseCase(repository)
    }

    @Test
    fun `invoke should return success when bus lines are found`() = runBlocking {
        val busLines = listOf(mockBusLine)
        coEvery { repository.searchBusLines("any") } returns busLines

        val result = busLineUseCase("any")

        assertTrue(result.isSuccess)
        assertEquals(busLines, result.getOrNull())
    }

    @Test
    fun `invoke should return UnknownException when an unexpected error occurs`() = runBlocking {
        coEvery { repository.searchBusLines("Crash") } throws RuntimeException()

        val result = busLineUseCase("Crash")

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.UnknownException)
    }
}
