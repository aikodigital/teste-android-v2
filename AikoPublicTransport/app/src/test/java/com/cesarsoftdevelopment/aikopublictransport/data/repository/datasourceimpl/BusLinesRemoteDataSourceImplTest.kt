package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class BusLinesRemoteDataSourceImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var publicTransportApi: PublicTransportApi

    private lateinit var busLinesDataSourceImpl: BusLinesRemoteDataSourceImpl

    @Before
    fun setUp() {
        busLinesDataSourceImpl = BusLinesRemoteDataSourceImpl(publicTransportApi)
    }

    @Test
    fun `test getBusLines returns expected list of bus lines`() = runBlocking {

        val mockBusLineItems = listOf(
            BusLineItem(
                null,
                101, false
                , "100A",
                1, 1,
                "Dest1",
                "Orig1",
                2,
                "Term1",
                "Term2",
                emptyList()
            )

        )
        val mockResponse = Response.success(mockBusLineItems)

        `when`(publicTransportApi.getBusLines("Lapa")).thenReturn(mockResponse)

        val response = busLinesDataSourceImpl.getBusLines("Lapa")

        assertNotNull(response)
        assertTrue(response.isSuccessful)
        assertEquals(mockBusLineItems.size, response.body()?.size)
        assertEquals(mockBusLineItems[0].lineCode, response.body()?.get(0)?.lineCode)
    }


}