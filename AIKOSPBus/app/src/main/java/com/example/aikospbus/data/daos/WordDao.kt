package com.example.aikospbus.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aikospbus.data.models.Word

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordList(wordList: List<Word>)

    @Query("SELECT * FROM word")
    suspend fun getAllWords(): List<Word>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWords(word: Word)
}