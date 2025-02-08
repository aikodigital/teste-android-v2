package hopeapps.dedev.sptrans.data.network

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val statusCode: Int) : ApiResponse<Nothing>()
}