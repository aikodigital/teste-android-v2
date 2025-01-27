package br.com.danilo.aikotestebus.domain.usecase

import android.net.http.HttpException
import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.repository.IBusRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Test
import java.io.IOException

class AuthenticatorUseCaseTest : BusTest() {

    private lateinit var useCase: AuthenticatorUseCase
    private lateinit var repository: IBusRepository
    private val mockAuthenticationResponse = true

    @Before
    fun setup() {
        repository = mockk()
        useCase = AuthenticatorUseCase(repository)
    }

    @Test
    fun `SHOULD return success WHEN repository call is successful for authenticate`() = coTest {
        coEvery { repository.authenticator() } returns mockAuthenticationResponse

        val result = useCase.authenticate().first()

        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), mockAuthenticationResponse)
    }

    @Test
    fun `should return failure when IOException occurs for authenticate`() = coTest {
        val ioException = IOException("Network error")

        coEvery { repository.authenticator() } throws ioException

        val result = useCase.authenticate().first()

        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), ioException)
    }

    @Test
    fun `should return failure when HttpException occurs for authenticate`() = coTest {
        val httpException = mockk<HttpException>()

        coEvery { repository.authenticator() } throws httpException

        val result = useCase.authenticate().first()

        assertTrue(result.isFailure)
        assertEquals(result.exceptionOrNull(), httpException)
    }
}