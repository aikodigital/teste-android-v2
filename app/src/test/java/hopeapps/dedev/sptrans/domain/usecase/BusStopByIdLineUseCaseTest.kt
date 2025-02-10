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
import java.io.IOException

@ExperimentalCoroutinesApi
class BusStopByIdLineUseCaseTest {

    private val repository: SearchRepository = mockk()
    private lateinit var useCase: BusStopByIdLineUseCase

    private val mockBusStopList = listOf(
        BusStop(1, "", "", 0.0, 0.0)
    )

    @Before
    fun setUp() {
        useCase = BusStopByIdLineUseCase(repository)
    }

    @Test
    fun `invoke should return success when repository returns a valid list`() = runBlocking {
        coEvery { repository.searchBusStopByBusLineId(10) } returns mockBusStopList

        val result = useCase(10)

        assertTrue(result.isSuccess)
        assertEquals(mockBusStopList, result.getOrNull())
    }

    @Test
    fun `invoke should return failure when repository throws an exception`() = runBlocking {
        coEvery { repository.searchBusStopByBusLineId(10) } throws IOException("Network Error")

        val result = useCase(10)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.UnknownException)
    }
}