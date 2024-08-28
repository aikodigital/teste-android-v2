package br.com.aiko.projetoolhovivo.data.service.position

import android.util.Log
import br.com.aiko.projetoolhovivo.data.model.position.PositionByListLine
import br.com.aiko.projetoolhovivo.data.model.position.PositionByVehicle
import retrofit2.Retrofit
import javax.inject.Inject

class PositionRepository @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getPositionByListLines(token: String): Result<PositionByListLine> {
        return try {
            val positionService: PositionService = retrofit.create(PositionService::class.java)
            val getPosition = positionService.getPositionByListLines(token)
            Result.success(getPosition)
        } catch (e: Exception) {
            Log.e("RETROFIT", e.message.toString())
            Log.e("RETROFIT", e.stackTrace.toString())
            Result.failure(e)
        }
    }

    suspend fun getPositionByVehicles(token: String, codeLine: Int): Result<PositionByVehicle> {
        return try {
            val positionService: PositionService = retrofit.create(PositionService::class.java)
            val getPosition = positionService.getPositionByVehicles(token, codeLine)
            Result.success(getPosition)
        } catch (e: Exception) {
            Log.e("RETROFIT", e.message.toString())
            Log.e("RETROFIT", e.stackTrace.toString())
            Result.failure(e)
        }
    }
}