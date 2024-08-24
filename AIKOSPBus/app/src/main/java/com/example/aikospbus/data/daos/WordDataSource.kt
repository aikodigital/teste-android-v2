package com.example.aikospbus.data.daos

import com.example.aikospbus.data.models.Word

interface WordDataSource {

    suspend fun insertWord(word: Word)

    suspend fun insertWordList(wordList: List<Word>)

    suspend fun getAllWords(): List<Word>

    suspend fun updateWords(word: Word)
}