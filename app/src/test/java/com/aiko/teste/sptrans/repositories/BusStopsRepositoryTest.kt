import com.aiko.teste.sptrans.data.APIService
import com.aiko.teste.sptrans.data.objects.BusStop
import com.aiko.teste.sptrans.data.objects.BusStopPrevisions
import com.aiko.teste.sptrans.data.repositories.BusStopsRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Response

class BusStopsRepositoryTest {

    private lateinit var repository: BusStopsRepository
    private val apiService = mock(APIService::class.java)
    private val cachedDataMock = listOf(
        BusStop("1", "Stop 1", 0.0, 0.0, listOf()),
        BusStop("2", "Stop 2", 0.0, 0.0, listOf()))

    @Before
    fun setUp() {
        repository = BusStopsRepository(apiService)
    }

    @Test
    fun `getBusStops returns cached data if available`() {
        repository = BusStopsRepository(apiService).apply {
            cachedData = cachedDataMock
        }
        val result = repository.getBusStops()

        assertTrue(result.isSuccess)
        assertEquals(cachedDataMock, result.getOrNull())
    }

    @Test
    fun `getBusStops fetches data when cache is empty`() {
        val mockCall = mock(Call::class.java) as Call<List<BusStop>>
        val response = Response.success(listOf(BusStop("1", "Stop 1", 0.0, 0.0, listOf())))

        `when`(apiService.getBusStops("")).thenReturn(mockCall)
        `when`(mockCall.execute()).thenReturn(response)

        val result = repository.getBusStops()

        assertTrue(result.isSuccess)
        assertEquals(response.body(), result.getOrNull())
    }

    @Test
    fun `getBusStopsByLine fetches bus stops by line code`() {
        val mockCall = mock(Call::class.java) as Call<List<BusStop>>
        val response = Response.success(listOf(BusStop("1", "Stop 1", 0.0, 0.0, listOf())))

        `when`(apiService.getBusStopsByLine("1")).thenReturn(mockCall)
        `when`(mockCall.execute()).thenReturn(response)

        val result = repository.getBusStopsByLine("1")

        assertTrue(result.isSuccess)
        assertEquals(response.body(), result.getOrNull())
    }

    @Test
    fun `getBusStop returns bus stop by code from cached data`() {
        repository = BusStopsRepository(apiService).apply {
            cachedData = cachedDataMock
        }
        val result = repository.getBusStop("1")

        assertEquals(cachedDataMock[0], result)
    }

    @Test
    fun `getBusStopPrevisions fetches bus stop previsions by stop code`() {
        val mockCall = mock(Call::class.java) as Call<BusStopPrevisions>
        val response = Response.success(BusStopPrevisions(BusStop("1", "Stop 1", 0.0, 0.0, listOf())))

        `when`(apiService.getBusStopPrevisions("1")).thenReturn(mockCall)
        `when`(mockCall.execute()).thenReturn(response)

        val result = repository.getBusStopPrevisions("1")

        assertTrue(result.isSuccess)
        assertEquals(response.body(), result.getOrNull())
    }
}
