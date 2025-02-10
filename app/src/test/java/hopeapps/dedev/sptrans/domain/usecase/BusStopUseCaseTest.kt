package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BusStopUseCaseTest {
    private lateinit var repository: SearchRepository
    private lateinit var busStopUseCase: BusStopUseCase
    private var mockBusStop = BusStop(1, "", "", 0.0, 0.0)

    @Before
    fun setup() {
        repository = mockk()
        busStopUseCase = BusStopUseCase(repository)
    }

    @Test
    fun `invoke should return success when bus stops are found`() = runBlocking {
        val busStops = listOf(mockBusStop)
        coEvery { repository.searchBusStop("any") } returns busStops
        val result = busStopUseCase("any")
        assertTrue(result.isSuccess)
        assertEquals(busStops, result.getOrNull())
    }

    @Test
    fun `invoke should return NullPointerException when API responds exceptions`() = runBlocking {
        coEvery { repository.searchBusStop("Unknown") } throws NullPointerException()

        val result = busStopUseCase("Unknown")

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.UnknownException)
    }
}
