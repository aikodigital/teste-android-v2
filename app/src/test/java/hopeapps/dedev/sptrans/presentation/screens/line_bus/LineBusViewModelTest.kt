package hopeapps.dedev.sptrans.presentation.screens.line_bus


import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.usecase.BusStopByIdLineUseCase
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LineBusViewModelTest: hopeapps.dedev.sptrans.core.Test() {
    private lateinit var viewModel: LineBusViewModel
    private val lineBusUseCase: BusStopByIdLineUseCase = mockk()

    private val mockBusStop = BusStop(idCodeBusStop = 1, name = "Test Stop", latitude = 40.0, longitude = -40.0, address = "")
    private val mockBusLine = BusLine(1,false,"", 1, 1,"","")

    @Before
    fun setUp() {
        viewModel = LineBusViewModel(lineBusUseCase)
    }

    @Test
    fun `should load bus stop items when the bus line is successfully loaded`() = coTest {
        val busLine = mockBusLine
        val busStopItems = listOf(mockBusStop, mockBusStop)
        coEvery { lineBusUseCase(busLine.lineId!!) } returns Result.success(busStopItems)
        viewModel.load(busLine)
        assertEquals(busStopItems, viewModel.state.busStopItems)
        assertEquals(false, viewModel.state.isLoading)
        assertEquals(null, viewModel.state.errorMessage)
    }

    @Test
    fun `should show error message when failing to load bus stop items`() = coTest {
        val busLine = mockBusLine
        val errorMessage = "Error loading bus stop items"
        coEvery { lineBusUseCase(busLine.lineId!!) } returns Result.failure(Exception(errorMessage))
        viewModel.load(busLine)
        assertEquals(emptyList<BusStop>(), viewModel.state.busStopItems)
        assertEquals(false, viewModel.state.isLoading)
        assertEquals(errorMessage, viewModel.state.errorMessage)
    }
}
