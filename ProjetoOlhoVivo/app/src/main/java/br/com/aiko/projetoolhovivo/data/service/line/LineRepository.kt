package br.com.aiko.projetoolhovivo.data.service.line

import android.util.Log
import br.com.aiko.projetoolhovivo.data.model.line.Line
import retrofit2.Retrofit
import javax.inject.Inject

class LineRepository @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getLines(token: String): Result<List<Line>> {
        return try {
            val lineService: LineService = retrofit.create(LineService::class.java)
            val getLines = lineService.getLines(token, "Lapa")
            Result.success(getLines)
        } catch (e: Exception) {
            Log.e("RETROFIT", e.message.toString())
            Log.e("RETROFIT", e.stackTrace.toString())
            Result.failure(e)
        }
    }
}