package br.com.danilo.aikotestebus.domain.usecase

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.model.BusStopLineResponse
import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.domain.mapper.toBusStopDetailList
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class BusStopByLineUseCaseTest : BusTest() {

    private lateinit var useCase: BusStopByLineUseCase
    private lateinit var repository: IBusRepository
    private val mockListStop = listOf(
        BusStopLineResponse(
            stopId = 0,
            name = "Stop 1",
            address = "Address 1",
            latitude = 0.0,
            longitude = 0.0
        )
    )

    @Before
    fun setup() {
        repository = mockk()
        useCase = BusStopByLineUseCase(repository)
    }

    @Test
    fun `SHOULD return Stops WHEN repository call is successful for getBusStopByLine`() = coTest {
        val idLine = 123

        coEvery { repository.getBusStopByLine(any()) } returns mockListStop

        val result = useCase.getBusStopByLine(idLine).first()
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), mockListStop.toBusStopDetailList())
    }

    @Test
    fun `SHOULD return failure when IOException occurs for getBusStopByLine`() = coTest {
        val idLine = 123
        val ioException = IOException("Network error")

        coEvery {
            repository.getBusStopByLine(idLine)
        } throws ioException

        val result = useCase.getBusStopByLine(idLine).first()
        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), ioException)
    }

    @Test
    fun `SHOULD return failure when HttpException occurs for getBusStopByLine`() = coTest {
        val idLine = 123
        val httpException = mockk<HttpException>()

        coEvery {
            repository.getBusStopByLine(idLine)
        } throws httpException

        val result = useCase.getBusStopByLine(idLine).first()
        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), httpException)
    }
}