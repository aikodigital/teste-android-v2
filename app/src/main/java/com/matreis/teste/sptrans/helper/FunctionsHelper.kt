package com.matreis.teste.sptrans.helper

import android.os.Build
import android.os.Bundle
import com.matreis.teste.sptrans.domain.model.Line
import com.matreis.teste.sptrans.domain.model.LineWithVehicles
import java.io.Serializable

inline fun <R> R?.orElse(block: () -> R): R {
    return this ?: block()
}

@Suppress("DEPRECATION")
inline fun <reified T : Serializable> Bundle.customGetSerializable(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, T::class.java)
    } else {
        getSerializable(key) as? T
    }
}

fun Line.getDirection(): String {
    return when(this.direction) {
        1 -> "${this.mainTerminalDescription} -> ${this.secondaryTerminalDescription}"
        2 -> "${this.secondaryTerminalDescription} -> ${this.mainTerminalDescription}"
        else -> "N/A"
    }
}

fun LineWithVehicles?.getDirection(): String {
    if(this == null) return "N/A"
    return when(this.direction) {
        1 -> "${this.destinationSign} -> ${this.originSign}"
        2 -> "${this.originSign} -> ${this.destinationSign}"
        else -> "N/A"
    }
}