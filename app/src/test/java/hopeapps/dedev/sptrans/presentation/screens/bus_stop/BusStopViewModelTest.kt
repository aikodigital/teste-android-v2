package hopeapps.dedev.sptrans.presentation.screens.bus_stop

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.usecase.BusStopPredictionUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BusStopViewModelTest : hopeapps.dedev.sptrans.core.Test() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: BusStopViewModel
    private val busStopPredictionUseCase: BusStopPredictionUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = BusStopViewModel(busStopPredictionUseCase)
    }

    private val mockBusStop = BusStop(idCodeBusStop = 1, name = "Test Stop", latitude = 40.0, longitude = -40.0, address = "")
    private val mockBusPrediction = BusPrediction(
        destination = "line.destination",
        origin = "line.origin",
        px = 0.0,
        py = 0.0,
        lastUpdateTime = "",
        predictionTime = "",
        accessibleVehicle = false,
        idLine = 1,
        firstToSecondTerminal = false
    )

    @Test
    fun `when load is called, it loads bus stop and triggers prediction request`() = coTest {
        val busPrediction = mockBusPrediction
        val busStop = mockBusStop
        val predictedBusStops = listOf(busPrediction)
        coEvery { busStopPredictionUseCase(busStop.idCodeBusStop) } returns Result.success(predictedBusStops)
        viewModel.load(busStop)
        assertEquals(viewModel.state.busStop, busStop)
        assertEquals(viewModel.state.busStopPrediction, predictedBusStops)
        assertEquals(viewModel.state.isLoading, false)

        coVerify { busStopPredictionUseCase(busStop.idCodeBusStop) }
    }

    @Test
    fun `when load fails, it sets error message in state`() = coTest {
        val busStop = BusStop(idCodeBusStop = 1, name = "Test Stop", latitude = 40.0, longitude = -40.0, address = "")
        val errorMessage = "Failed to fetch predictions"
        coEvery { busStopPredictionUseCase(busStop.idCodeBusStop) } returns Result.failure(Throwable(errorMessage))
        viewModel.load(busStop)
        assertEquals(viewModel.state.errorMessage, errorMessage)
        assertEquals(viewModel.state.isLoading, false)
        coVerify { busStopPredictionUseCase(busStop.idCodeBusStop) }
    }
}