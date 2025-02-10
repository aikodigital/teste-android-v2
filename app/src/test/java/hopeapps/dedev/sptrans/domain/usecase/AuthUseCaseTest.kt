package hopeapps.dedev.sptrans.domain.usecase

import hopeapps.dedev.sptrans.domain.exceptions.DomainException
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class AuthUseCaseTest {
    private lateinit var repository: SearchRepository
    private lateinit var authUseCase: AuthUseCase

    @Before
    fun setUp() {
        repository = mockk()
        authUseCase = AuthUseCase(repository)
    }

    @Test
    fun `invoke should return success when authentication is successful`() = runBlocking {
        coEvery { repository.authApi() } returns true
        val result = authUseCase()
        assertTrue(result.isSuccess)
    }

    @Test
    fun `invoke should return AuthenticationException when authentication fails`() = runBlocking {
        coEvery { repository.authApi() } returns false
        val result = authUseCase()
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.AuthenticationException)
    }

    @Test
    fun `invoke should return UnknownException when an unexpected error occurs`() = runBlocking {
        coEvery { repository.authApi() } throws RuntimeException()
        val result = authUseCase()
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is DomainException.UnknownException)
    }
}