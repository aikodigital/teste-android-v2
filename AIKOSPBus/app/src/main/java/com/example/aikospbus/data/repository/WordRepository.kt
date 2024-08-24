package com.example.aikospbus.data.repository

import com.example.aikospbus.data.models.Word

interface WordRepository {

    suspend fun insertWord(word: Word)

    suspend fun insertWordList(wordList: List<Word>)

    suspend fun getAllWords(): List<Word>

    suspend fun updateWords(word: Word)
}