package br.com.danilo.aikotestebus.presentation.features.stopbyline

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.model.BusStopLineResponse
import br.com.danilo.aikotestebus.domain.mapper.toBusStopDetailList
import br.com.danilo.aikotestebus.domain.usecase.BusStopByLineUseCase
import br.com.danilo.aikotestebus.presentation.util.state.BusStopByLineState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flow
import org.junit.Test

class BusStopByLineViewModelTest : BusTest() {

    private val mockUseCase = mockk<BusStopByLineUseCase>(relaxed = true)
    private val viewModel = BusStopByLineViewModel(mockUseCase)

    private val mockStopDetailList = listOf(
        BusStopLineResponse(
            stopId = 0,
            name = "Stop 1",
            address = "Address 1",
            latitude = 0.0,
            longitude = 0.0
        )
    )
    @Test
    fun `should fetch data successfully and filter`() = coTest {
        coEvery { mockUseCase.getBusStopByLine(1) } returns flow { emit(Result.success(mockStopDetailList.toBusStopDetailList())) }

        viewModel.fetchBusStopByLine(1)

        assertTrue(viewModel.uiState.value is BusStopByLineState.Success)
        assertEquals(mockStopDetailList.toBusStopDetailList(), (viewModel.uiState.value as BusStopByLineState.Success).items)
    }

    @Test
    fun `should handle error on fetch data function`() = coTest {
        val expectedError = Exception("error fetching data")
        coEvery { mockUseCase.getBusStopByLine(1) } returns flow { emit(Result.failure(expectedError)) }

        viewModel.fetchBusStopByLine(1)

        assertTrue(viewModel.uiState.value is BusStopByLineState.Error)
    }

    @Test
    fun `should update search query and filter data`() = coTest {
        coEvery { mockUseCase.getBusStopByLine(1) } returns flow { emit(Result.success(mockStopDetailList.toBusStopDetailList())) }

        viewModel.fetchBusStopByLine(1)

        viewModel.onSearchQueryChange("Stop 1")

        val filteredList = (viewModel.uiState.value as BusStopByLineState.Success).items
        assertEquals(1, filteredList.size) // Deve retornar apenas "Stop 1"
    }

    @Test
    fun `should set loading state while fetching data`() = coTest {
        coEvery { mockUseCase.getBusStopByLine(1) } returns flow { emit(Result.success(mockStopDetailList.toBusStopDetailList())) }

        assertTrue(viewModel.uiState.value is BusStopByLineState.Loading)
    }
}