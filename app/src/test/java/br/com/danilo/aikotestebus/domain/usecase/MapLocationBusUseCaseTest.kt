package br.com.danilo.aikotestebus.domain.usecase

import android.net.http.HttpException
import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.repository.IBusRepository
import br.com.danilo.aikotestebus.domain.mapper.toBusesPosition
import br.com.danilo.aikotestebus.mock.mockBusesPositionResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Test
import java.io.IOException

class MapLocationBusUseCaseTest : BusTest() {

    private lateinit var useCase: MapLocationBusUseCase
    private lateinit var repository: IBusRepository

    @Before
    fun setup() {
        repository = mockk()
        useCase = MapLocationBusUseCase(repository)
    }

    @Test
    fun `SHOULD return buses position WHEN repository call is successful for getBusesPosition`() = coTest {
        coEvery { repository.getBusesPosition() } returns mockBusesPositionResponse

        val result = useCase.getBusesPosition().first()

        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), mockBusesPositionResponse.toBusesPosition())
    }

    @Test
    fun `should return failure when IOException occurs for getBusesPosition`() = coTest {
        val ioException = IOException("Network error")

        coEvery { repository.getBusesPosition() } throws ioException

        val result = useCase.getBusesPosition().first()

        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), ioException)
    }

    @Test
    fun `should return failure when HttpException occurs for getBusesPosition`() = coTest {
        val httpException = mockk<HttpException>()

        coEvery { repository.getBusesPosition() } throws httpException

        val result = useCase.getBusesPosition().first()

        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), httpException)
    }
}