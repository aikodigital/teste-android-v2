package br.com.danilo.aikotestebus.domain.usecase

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.data.repository.IBusRepositoryImpl
import br.com.danilo.aikotestebus.domain.mapper.toLineDetailList
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class LineBusDetailUseCaseTest : BusTest() {

    private lateinit var useCase: LineBusDetailUseCase
    private lateinit var repository: IBusRepositoryImpl
    private val mockListLine = listOf(
        LineDetailResponse(
            0, false, "", 0, 0, "", "",
        )
    )

    @Before
    fun setup() {
        repository = mockk()
        useCase = LineBusDetailUseCase(repository)
    }

    @Test
    fun `SHOULD return Lines WHEN repository call is successful for getBusLine`() = coTest {
        val query = "sample query"

        coEvery { repository.getBusLine(any()) } returns mockListLine

        val result = useCase.getBusLine(query).first()
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), mockListLine.toLineDetailList())
    }

    @Test
    fun `should return failure when IOException occurs for getBusLine`() = coTest {
        val query = "sample query"
        val ioException = IOException("Network error")

        coEvery {
            repository.getBusLine(query)
        } throws ioException

        val result = useCase.getBusLine(query).first()
        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), ioException)
    }

    @Test
    fun `should return failure when HttpException occurs for getBusLine`() = coTest {
        val query = "sample query"
        val httpException = mockk<HttpException>()

        coEvery {
            repository.getBusLine(query)
        } throws httpException

        val result = useCase.getBusLine(query).first()
        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), httpException)
    }
}