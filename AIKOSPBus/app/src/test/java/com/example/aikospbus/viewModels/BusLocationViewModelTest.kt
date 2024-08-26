package com.example.aikospbus.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.aikospbus.data.source.FakeBusLocationRepository
import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import com.example.aikospbus.feature_bus_location.data.remote.dto.VehicleDto
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.feature_bus_location.domain.use_case.GetRemoteBusLocationDataUseCase
import com.example.aikospbus.feature_bus_location.presentation.BusLocationViewModel
import com.example.aikospbus.util.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BusLocationViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var busLocationRepository: FakeBusLocationRepository
    private lateinit var busLocationViewModel: BusLocationViewModel
    private lateinit var getRemoteBusLocationDataUseCase: GetRemoteBusLocationDataUseCase

    private val vehicleDtos =
        listOf(
            VehicleDto(
                123,
                true,
                "12:00",
                12.345,
                67.891,
                "teste"
            )
        )

    private var fakeBusLocationData: BusLocationModel = BusLocationModel(
        "12:00",
        vehicleDtos,
    )

    private var fakeList: List<BusLocationModel> = listOf(
        fakeBusLocationData
    )

    @Before
    fun setUp() {
        busLocationRepository = FakeBusLocationRepository()
        busLocationRepository.insertAllBusLocationData(fakeList)
        getRemoteBusLocationDataUseCase = GetRemoteBusLocationDataUseCase(busLocationRepository)
        busLocationViewModel = BusLocationViewModel(
            getRemoteBusLocationDataUseCase
        )
    }

    @Test
    fun getRemoteConnectivityDataTest() {
        busLocationViewModel.getRemoteBusLocationData("abc",123)

        val expectedVehicle =
            listOf(
                VehicleDto(
                    123,
                    true,
                    "12:00",
                    12.345,
                    67.891,
                    "teste"
                )
            )

        val expectedBusLocation = BusLocationModel(
            horaConsulta = "12:00",
            vehicleDtos = expectedVehicle
        )

        val observedData = busLocationViewModel.busDtoLocationDataModel.value

        Assert.assertEquals(expectedBusLocation, observedData)
    }


}