package br.com.danilo.aikotestebus.presentation.features.arrivalforecast

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.domain.model.ArrivalForecast
import br.com.danilo.aikotestebus.domain.model.ArrivalForecastStop
import br.com.danilo.aikotestebus.domain.usecase.ArrivalForecastUseCase
import br.com.danilo.aikotestebus.presentation.util.state.ArrivalForecastState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.lang.reflect.Field

class ArrivalForecastViewModelTest : BusTest() {

    private val mockUseCase = mockk<ArrivalForecastUseCase>(relaxed = true)
    private val viewModel = ArrivalForecastViewModel(mockUseCase)

    @Test
    fun `should fetch arrival forecast successfully`() = coTest {
        val mockForecastData =
            ArrivalForecast("", ArrivalForecastStop(0, "", 0.0, 0.0, listOf())) // Exemplo de dados
        coEvery {
            mockUseCase.getArrivalForecastTime(
                any(),
                any()
            )
        } returns flow { emit(Result.success(mockForecastData)) }

        viewModel.fetchArrivalForecast(1, 101)

        assertTrue(viewModel.uiState.value is ArrivalForecastState.Success)
        assertEquals(
            mockForecastData,
            (viewModel.uiState.value as ArrivalForecastState.Success).item
        )
    }

    @Test
    fun `should handle error on fetch arrival forecast`() = coTest {
        val expectedError = Exception("Error fetching forecast")
        coEvery {
            mockUseCase.getArrivalForecastTime(
                any(),
                any()
            )
        } returns flow { emit(Result.failure(expectedError)) }

        viewModel.fetchArrivalForecast(1, 101)

        assertTrue(viewModel.uiState.value is ArrivalForecastState.Error)
    }

    @Test
    fun `should access and test private periodicTaskJob`() {
        val field: Field = ArrivalForecastViewModel::class.java.getDeclaredField("periodicTaskJob")
        field.isAccessible = true

        val jobValue = field.get(viewModel) as Job?

        assertEquals(null, jobValue)

        viewModel.startPeriodicTask(1, 101)

        val jobValueAfterStart = field.get(viewModel) as Job?

        assertTrue(jobValueAfterStart?.isActive == true)

        viewModel.stopPeriodicTask()

        val jobValueAfterStop = field.get(viewModel) as Job?

        assertTrue(jobValueAfterStop?.isActive == false)
    }
}