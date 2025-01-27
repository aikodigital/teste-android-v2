package br.com.danilo.aikotestebus.presentation.features.maplocation

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.domain.mapper.toBusesPosition
import br.com.danilo.aikotestebus.domain.usecase.MapLocationBusUseCase
import br.com.danilo.aikotestebus.mock.mockBusesPositionResponse
import br.com.danilo.aikotestebus.presentation.util.state.MapLocationBusState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import org.junit.Test
import java.lang.reflect.Field

class MapLocationBusViewModelTest : BusTest() {

    private val mockUseCase = mockk<MapLocationBusUseCase>(relaxed = true)
    private val viewModel = MapLocationBusViewModel(mockUseCase)

    @Test
    fun `should fetch location successfully`() = coTest {
        coEvery { mockUseCase.getBusesPosition() } returns flow {
            emit(
                Result.success(
                    mockBusesPositionResponse.toBusesPosition()
                )
            )
        }

        viewModel.startPeriodicLocationTask()

        assertTrue(viewModel.uiState.value is MapLocationBusState.Success)
        assertEquals(
            mockBusesPositionResponse.toBusesPosition(),
            (viewModel.uiState.value as MapLocationBusState.Success).items
        )

        viewModel.stopPeriodicLocationTask()
    }

    @Test
    fun `should handle error on fetch arrival forecast`() = coTest {
        val expectedError = Exception("Error fetching forecast")
        coEvery { mockUseCase.getBusesPosition() } returns flow { emit(Result.failure(expectedError)) }

        viewModel.startPeriodicLocationTask()

        assertTrue(viewModel.uiState.value is MapLocationBusState.Error)

        viewModel.stopPeriodicLocationTask()
    }

    @Test
    fun `should access and test private periodicTaskJob`() {
        val field: Field = MapLocationBusViewModel::class.java.getDeclaredField("periodicTaskJob")
        field.isAccessible = true

        val jobValue = field.get(viewModel) as Job?
        assertEquals(null, jobValue)

        viewModel.startPeriodicLocationTask()

        val jobValueAfterStart = field.get(viewModel) as Job?
        assertTrue(jobValueAfterStart?.isActive == true)

        viewModel.stopPeriodicLocationTask()

        val jobValueAfterStop = field.get(viewModel) as Job?
        assertTrue(jobValueAfterStop?.isActive == false)
    }

}
