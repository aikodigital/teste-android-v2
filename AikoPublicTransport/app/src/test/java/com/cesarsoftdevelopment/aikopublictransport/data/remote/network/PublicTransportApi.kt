package com.cesarsoftdevelopment.aikopublictransport.data.remote.network

import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PublicTransportApiTest {

    private lateinit var service : PublicTransportApi
    private lateinit var server : MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PublicTransportApi::class.java)
    }

    private fun enqueueMockResponse(filename : String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filename)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getBusLines_sentRequest_receivedExpected(){

        runBlocking {
            enqueueMockResponse(filename = "buslinesresponse.json")
            val responseBody = service.getBusLines("Lapa").body()
            val request = server.takeRequest()
            Truth.assertThat(responseBody).isNotNull()
            Truth.assertThat(request.path).isEqualTo("/Linha/Buscar?termosBusca=Lapa")
        }
    }

    @Test
    fun getBusLines_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse(filename = "buslinesresponse.json")
            val responseBody = service.getBusLines("Lapa").body()
            val articlesList = responseBody!![0]
                Truth.assertThat(articlesList.lineCode).isEqualTo(841)
                Truth.assertThat(articlesList.circularLine).isEqualTo(false)
                Truth.assertThat(articlesList.firstLineNumber).isEqualTo("118Y")
                Truth.assertThat(articlesList.lineDirection).isEqualTo(1)
                Truth.assertThat(articlesList.primaryTerminal).isEqualTo("LAPA")
                Truth.assertThat(articlesList.secondaryTerminal).isEqualTo("LAUZANE PAULISTA")
            }


        }

    @After
    fun tearDown() {
        server.shutdown()
    }


}

