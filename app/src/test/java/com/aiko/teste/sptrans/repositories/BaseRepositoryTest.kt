import com.aiko.teste.sptrans.data.repositories.BaseRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Response

class BaseRepositoryTest {

    private val repository = BaseRepository()

    @Test
    fun `makeApiCall returns success when API call is successful`() {
        val expectedData = "Success"
        val mockCall = mock(Call::class.java) as Call<*>
        val response = Response.success(expectedData)

        `when`(mockCall.execute()).thenReturn(response)

        val result = repository.makeApiCall { mockCall.execute() }

        assertTrue(result.isSuccess)
        assertEquals(expectedData, result.getOrNull())
    }

    @Test
    fun `makeApiCall returns failure when API call returns null body`() {
        val mockCall = mock(Call::class.java) as Call<*>
        val response = Response.success<String>(null)

        `when`(mockCall.execute()).thenReturn(response)

        val result = repository.makeApiCall { mockCall.execute() }

        assertTrue(result.isFailure)
        assertEquals("Response body is null", result.exceptionOrNull()?.message)
    }

    @Test
    fun `makeApiCall returns failure when API call fails with error code`() {
        val mockCall = mock(Call::class.java) as Call<*>
        val response = Response.error<String>(404, mock(okhttp3.ResponseBody::class.java))

        `when`(mockCall.execute()).thenReturn(response)

        val result = repository.makeApiCall { mockCall.execute() }

        assertTrue(result.isFailure)
        assertEquals("API error: 404", result.exceptionOrNull()?.message)
    }

    @Test
    fun `makeApiCall returns failure when API call throws exception`() {
        val mockCall = mock(Call::class.java) as Call<*>
        val exception = RuntimeException("Network error")

        `when`(mockCall.execute()).thenThrow(exception)

        val result = repository.makeApiCall { mockCall.execute() }

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
