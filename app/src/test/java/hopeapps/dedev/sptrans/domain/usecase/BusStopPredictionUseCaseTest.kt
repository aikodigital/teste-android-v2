package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.models.BusPrediction
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
class BusStopPredictionUseCaseTest {

    private lateinit var useCase: BusStopPredictionUseCase
    private val repository: SearchRepository = mockk()

    @Before
    fun setUp() {
        useCase = BusStopPredictionUseCase(repository)
    }

    private val mockExpectedPrediction = BusPrediction("","",0.0, 0.0, lastUpdateTime = "", predictionTime = "", "")

    @Test
    fun `invoke should return success when bus predictions are found`() = runBlocking {
        val predictions = listOf(mockExpectedPrediction,mockExpectedPrediction)
        coEvery { repository.searchBusStopPrediction(123) } returns ApiResponse.Success(predictions)
        val result = useCase(123)
        assertTrue(result.isSuccess)
        assertEquals(predictions, result.getOrNull())
    }

    @Test
    fun `invoke should return NotFoundException when API responds with 404 error`() = runBlocking {
        coEvery { repository.searchBusStopPrediction(123) } returns ApiResponse.Error(404)
        val result = useCase(123)
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.NotFoundException)
    }

    @Test
    fun `invoke should return ServerException when API responds with 500 error`() = runBlocking {
        coEvery { repository.searchBusStopPrediction(123) } returns ApiResponse.Error(500)
        val result = useCase(123)
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.ServerException)
    }

    @Test
    fun `invoke should return UnknownException when an unexpected error occurs`() = runBlocking {
        coEvery { repository.searchBusStopPrediction(123) } throws RuntimeException("Unexpected Error")
        val result = useCase(123)
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.UnknownException)
    }
}
