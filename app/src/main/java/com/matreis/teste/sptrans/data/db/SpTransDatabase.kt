package com.matreis.teste.sptrans.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matreis.teste.sptrans.domain.model.Line

@Database( entities = [Line:: class], version = 1, exportSchema = false )
abstract class SpTransDatabase: RoomDatabase() {
    abstract fun lineDao(): LineDao
}