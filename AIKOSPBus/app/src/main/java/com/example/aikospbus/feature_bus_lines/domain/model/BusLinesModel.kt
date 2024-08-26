package com.example.aikospbus.feature_bus_lines.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busLines")
class BusLinesModel(
    @PrimaryKey(autoGenerate = false)
    val codigoLinha: Int,
    val circular: Boolean,
    val letreiro: String,
    val sentido: Int,
    val tipo: Int,
    val terminalPrincipal: String,
    val terminalSecundario: String
)
