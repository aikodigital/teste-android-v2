package br.com.danilo.aikotestebus.domain.usecase

import android.net.http.HttpException
import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.model.ArrivalForecastResponse
import br.com.danilo.aikotestebus.data.model.ArrivalForecastStopResponse
import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.domain.mapper.toArrivalForecast
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ArrivalForecastUseCaseTest : BusTest() {

    private lateinit var useCase: ArrivalForecastUseCase
    private lateinit var repository: IBusRepository
    private val mockArrivalForecast =  ArrivalForecastResponse(
        "20/3",
        ArrivalForecastStopResponse(
            0, "23", 112223.321, 12313.312, listOf()
        )
    )

    @Before
    fun setup() {
        repository = mockk()
        useCase = ArrivalForecastUseCase(repository)
    }

    @Test
    fun `SHOULD return ArrivalForecast WHEN repository call is successful for getArrivalForecastTime`() = coTest {
        val idStop = 456
        val idLine = 123

        coEvery { repository.getArrivalForecastTime(any(), any()) } returns mockArrivalForecast

        val result = useCase.getArrivalForecastTime(idStop, idLine).first()
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), mockArrivalForecast.toArrivalForecast())
    }

    @Test
    fun `SHOULD return failure when IOException occurs for getArrivalForecastTime`() = coTest {
        val idStop = 456
        val idLine = 123
        val ioException = IOException("Network error")

        coEvery {
            repository.getArrivalForecastTime(idStop, idLine)
        } throws ioException

        val result = useCase.getArrivalForecastTime(idStop, idLine).first()
        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), ioException)
    }

    @Test
    fun `SHOULD return failure when HttpException occurs for getArrivalForecastTime`() = coTest {
        val idStop = 456
        val idLine = 123
        val httpException = mockk<HttpException>()

        coEvery {
            repository.getArrivalForecastTime(idStop, idLine)
        } throws httpException

        val result = useCase.getArrivalForecastTime(idStop, idLine).first()
        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), httpException)
    }
}