import com.aiko.teste.sptrans.data.APIService
import com.aiko.teste.sptrans.data.objects.BusLine
import com.aiko.teste.sptrans.data.repositories.BusLinesRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.Before
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Response

class BusLinesRepositoryTest {

    private lateinit var apiService: APIService
    private lateinit var repository: BusLinesRepository
    private lateinit var mockCall: Call<List<BusLine>>
    private lateinit var mockSingleCall: Call<BusLine>

    @Before
    fun setUp() {
        apiService = mock(APIService::class.java)
        repository = BusLinesRepository(apiService)
        mockCall = mock(Call::class.java) as Call<List<BusLine>>
        mockSingleCall = mock(Call::class.java) as Call<BusLine>
    }

    @Test
    fun `getBusLines returns success when API call is successful`() {
        val searchTerms = "someSearchTerm"
        val expectedBusLines = listOf(BusLine("123", "Line 1", "Start - End"))
        val response = Response.success(expectedBusLines)

        `when`(mockCall.execute()).thenReturn(response)
        `when`(apiService.getBusLines(searchTerms)).thenReturn(mockCall)

        val result = repository.getBusLines(searchTerms)

        assertTrue(result.isSuccess)
        assertEquals(expectedBusLines, result.getOrNull())
    }

    @Test
    fun `getBusLines returns failure when API call fails`() {
        val searchTerms = "someSearchTerm"
        val response = Response.error<List<BusLine>>(404, mock(okhttp3.ResponseBody::class.java))

        `when`(mockCall.execute()).thenReturn(response)
        `when`(apiService.getBusLines(searchTerms)).thenReturn(mockCall)

        val result = repository.getBusLines(searchTerms)

        assertTrue(result.isFailure)
    }

    @Test
    fun `getBusPositions returns success when API call is successful`() {
        val busLineCode = "123"
        val expectedBusLine = BusLine(busLineCode, "Line 1", "Start - End")
        val response = Response.success(expectedBusLine)

        `when`(mockSingleCall.execute()).thenReturn(response)
        `when`(apiService.getBusPositionsFromLine(busLineCode)).thenReturn(mockSingleCall)

        val result = repository.getBusPositions(busLineCode)

        assertTrue(result.isSuccess)
        assertEquals(expectedBusLine, result.getOrNull())
    }

    @Test
    fun `getBusPositions returns failure when API call fails`() {
        val busLineCode = "123"
        val response = Response.error<BusLine>(404, mock(okhttp3.ResponseBody::class.java))

        `when`(mockSingleCall.execute()).thenReturn(response)
        `when`(apiService.getBusPositionsFromLine(busLineCode)).thenReturn(mockSingleCall)

        val result = repository.getBusPositions(busLineCode)

        assertTrue(result.isFailure)
    }
}
