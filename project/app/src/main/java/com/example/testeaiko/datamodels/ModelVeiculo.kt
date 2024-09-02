package com.example.testeaiko.datamodels

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class ModelVeiculo(
        val p: Int,       // Prefixo do veículo
        val t: String? = null, // tempo de chegada na parada
        val a: Boolean,   // Indica se o veículo é acessível para pessoas com deficiência
        val ta: String,   // Horário universal (UTC) da localização capturada no padrão ISO 8601
        val py: Double,   // Latitude da localização do veículo
        val px: Double    // Longitude da localização do veículo
) {
    val arrivalTimeFormatted: String
        get() {
            if (t == null) return "Erro"
            val t2 = t.split(":")
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val t1 = dateFormat.format(Calendar.getInstance().time).split(":")
            val min2 = t2[0].toInt() * 60 + t2[1].toInt()
            val min1 = t1[0].toInt() * 60 + t1[1].toInt()
            return "chega em ${min2 - min1} minutos."
        }

}