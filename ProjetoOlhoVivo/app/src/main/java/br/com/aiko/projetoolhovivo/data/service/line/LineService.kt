package br.com.aiko.projetoolhovivo.data.service.line

import br.com.aiko.projetoolhovivo.data.model.line.Line
import retrofit2.http.GET
import retrofit2.http.Query

interface LineService {
    @GET("Linha/Buscar")
    suspend fun getLines(
        @Query("token") token: String, @Query("termosBusca") search: String
    ): List<Line>
}