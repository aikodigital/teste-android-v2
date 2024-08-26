package com.example.aikospbus.data.repository

import com.example.aikospbus.data.source.FakeBusLocationDataSource
import com.example.aikospbus.data.source.FakeRemoteBusLocationDataSource
import com.example.aikospbus.feature_bus_location.data.remote.dto.VehicleDto
import com.example.aikospbus.feature_bus_location.data.repository.BusLocationRepositoryImpl
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class BusLocationRepositoryImplTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

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

    private val busLocation = BusLocationModel(
        "12:00",
        vehicleDtos,
    )

    private lateinit var busLocationDataSource: FakeBusLocationDataSource
    private lateinit var busLocationRemoteDataSource: FakeRemoteBusLocationDataSource
    private lateinit var busLocationRepositoryImpl: BusLocationRepositoryImpl

    @Before
    fun setUpRepository() {
        busLocationDataSource = FakeBusLocationDataSource(busLocation)
        busLocationRemoteDataSource = FakeRemoteBusLocationDataSource()
        busLocationRepositoryImpl = BusLocationRepositoryImpl(
            busLocationDataSource, busLocationRemoteDataSource
        )
    }
}