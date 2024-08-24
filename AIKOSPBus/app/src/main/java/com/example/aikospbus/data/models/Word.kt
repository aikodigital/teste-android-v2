package com.example.aikospbus.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String = ""
)
