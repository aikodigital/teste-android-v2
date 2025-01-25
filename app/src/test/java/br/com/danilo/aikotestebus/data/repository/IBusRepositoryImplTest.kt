package br.com.danilo.aikotestebus.data.repository

import br.com.danilo.aikotestebus.core.BusTest
import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.data.service.BusApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class IBusRepositoryImplTest : BusTest(){

    private val busApiService = mockk<BusApiService>()
    private val busRepository = IBusRepositoryImpl(busApiService)
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
}