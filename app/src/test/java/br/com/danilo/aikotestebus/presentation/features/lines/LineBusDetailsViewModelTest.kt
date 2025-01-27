package br.com.danilo.aikotestebus.presentation.features.lines

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.domain.mapper.toLineDetailList
import br.com.danilo.aikotestebus.domain.usecase.LineBusDetailUseCase
import br.com.danilo.aikotestebus.presentation.util.state.LineBusDetailState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flow
import org.junit.Test

class LineBusDetailsViewModelTest : BusTest() {

    private val mockUseCase = mockk<LineBusDetailUseCase>(relaxed = true)
    private val viewModel = LineBusDetailsViewModel(mockUseCase)
    private val mockListLine = listOf(
        LineDetailResponse(
            0, false, "", 0, 0, "", "",
        )
    )

    @Test
    fun `should fetchData successfully`() = coTest {
        coEvery { mockUseCase.getBusLine(any()) } returns flow { emit(Result.success(mockListLine.toLineDetailList())) }

        viewModel.fetchLines("fetch")

        assertNotNull((viewModel.uiState.value as LineBusDetailState.Success).items)
        assertEquals(mockListLine.toLineDetailList(), (viewModel.uiState.value as LineBusDetailState.Success).items)
    }

    @Test
    fun `should handle error on fetchData function`() = coTest {
        val expectedError = Exception("error fetch data")
        coEvery { mockUseCase.getBusLine("fetch") } returns flow { emit(Result.failure(expectedError)) }

        viewModel.fetchLines("fetch")

        assertTrue(viewModel.uiState.value is LineBusDetailState.Error)
    }

    @Test
    fun `should set loading state while fetching`() = coTest {
        viewModel.fetchLines("fetch")

        assertTrue(viewModel.uiState.value is LineBusDetailState.Loading)
    }
}