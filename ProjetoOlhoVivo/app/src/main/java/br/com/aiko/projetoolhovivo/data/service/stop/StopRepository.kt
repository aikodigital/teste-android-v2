package br.com.aiko.projetoolhovivo.data.service.stop

import android.util.Log
import br.com.aiko.projetoolhovivo.data.model.stop.Stop
import retrofit2.Retrofit
import javax.inject.Inject

class StopRepository @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getStops(token: String): Result<List<Stop>> {
        return try {
            val stopService: StopService = retrofit.create(StopService::class.java)
            val getStop = stopService.getStops(token, "Lapa")
            Result.success(getStop)
        } catch (e: Exception) {
            Log.e("RETROFIT", e.message.toString())
            Log.e("RETROFIT", e.stackTrace.toString())
            Result.failure(e)
        }
    }

    suspend fun getStopsByCodeLine(token: String, codeLine: Int): Result<List<Stop>> {
        return try {
            val stopService: StopService = retrofit.create(StopService::class.java)
            val getStop = stopService.getStopsByCodeLine(token, codeLine)
            Result.success(getStop)
        } catch (e: Exception) {
            Log.e("RETROFIT", e.message.toString())
            Log.e("RETROFIT", e.stackTrace.toString())
            Result.failure(e)
        }
    }
}