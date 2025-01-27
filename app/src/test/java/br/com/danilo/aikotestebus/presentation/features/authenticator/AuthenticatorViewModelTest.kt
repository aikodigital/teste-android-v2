package br.com.danilo.aikotestebus.presentation.features.authenticator

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.domain.usecase.AuthenticatorUseCase
import br.com.danilo.aikotestebus.presentation.util.state.AuthenticatorState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flow
import org.junit.Test

class AuthenticatorViewModelTest : BusTest() {

    private val mockUseCase = mockk<AuthenticatorUseCase>(relaxed = true)
    private val viewModel = AuthenticatorViewModel(mockUseCase)

    @Test
    fun `should authenticate successfully`() = coTest {
        coEvery { mockUseCase.authenticate() } returns flow { emit(Result.success(true)) }

        viewModel.fetchAuthenticator()

        assertTrue(viewModel.uiState.value is AuthenticatorState.Success)
    }

    @Test
    fun `should handle authentication error`() = coTest {
        val expectedError = Exception("Authentication failed")
        coEvery { mockUseCase.authenticate() } returns flow { emit(Result.failure(expectedError)) }

        viewModel.fetchAuthenticator()

        assertTrue(viewModel.uiState.value is AuthenticatorState.Error)
    }

    @Test
    fun `should set loading state while authenticating`() = coTest {
        viewModel.fetchAuthenticator()

        assertTrue(viewModel.uiState.value is AuthenticatorState.Loading)
    }
}