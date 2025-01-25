package br.com.danilo.aikotestebus.data.repository

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.data.service.BusApiService
import br.com.danilo.aikotestebus.mock.mockArrivalForecastResponse
import br.com.danilo.aikotestebus.mock.mockBusesPositionResponse
import br.com.danilo.aikotestebus.mock.mockStopDetailResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Test

class BusRepositoryImplTest : BusTest(){

    private val busApiService = mockk<BusApiService>()
    private val busRepository = BusRepositoryImpl(busApiService)
    private val mockListLine = listOf(LineDetailResponse(
    0, false, "", 0, 0, "", "",)
    )

    @Test
    fun `WHEN getBusLine is called THEN verifiy result is not null and THEN verify the body and is called one time`() = coTest {
        coEvery { busApiService.getBusLine(any()) } returns mockListLine

        val result = busRepository.getBusLine("")

        assertNotNull(result)
        assertEquals(mockListLine, result)
        coVerify(exactly = 1) { busApiService.getBusLine(any()) }
    }

    @Test
    fun `WHEN authenticator is called THEN verify result is not null and is called one time`() = coTest {
        coEvery { busApiService.authenticator() } returns true

        val result = busRepository.authenticator()

        assertNotNull(result)
        assertTrue(result)
        coVerify(exactly = 1) { busApiService.authenticator() }
    }

    @Test
    fun `WHEN getBusLine is called THEN verify result is not null and THEN verify the body and is called one time`() = coTest {
        coEvery { busApiService.getBusLine(any()) } returns mockListLine

        val result = busRepository.getBusLine("")

        assertNotNull(result)
        assertEquals(mockListLine, result)
        coVerify(exactly = 1) { busApiService.getBusLine(any()) }
    }

    @Test
    fun `WHEN getBusesPosition is called THEN verify result is not null and is called one time`() = coTest {
        coEvery { busApiService.getBusesPosition() } returns mockBusesPositionResponse

        val result = busRepository.getBusesPosition()

        assertNotNull(result)
        assertEquals(mockBusesPositionResponse, result)
        coVerify(exactly = 1) { busApiService.getBusesPosition() }
    }

    @Test
    fun `WHEN getBusStop is called THEN verify result is not null and is called one time`() = coTest {
        coEvery { busApiService.getBusStop(any()) } returns mockStopDetailResponse

        val result = busRepository.getBusStop("")

        assertNotNull(result)
        assertEquals(mockStopDetailResponse, result)
        coVerify(exactly = 1) { busApiService.getBusStop(any()) }
    }

    @Test
    fun `WHEN getArrivalForecastTime is called THEN verify result is not null and is called one time`() = coTest {
        coEvery { busApiService.getArrivalForecastTime(any(), any()) } returns mockArrivalForecastResponse

        val result = busRepository.getArrivalForecastTime("", "")

        assertNotNull(result)
        assertEquals(mockArrivalForecastResponse, result)
        coVerify(exactly = 1) { busApiService.getArrivalForecastTime(any(), any()) }
    }
}