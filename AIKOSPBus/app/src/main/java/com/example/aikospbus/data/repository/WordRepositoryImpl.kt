package com.example.aikospbus.data.repository

import com.example.aikospbus.data.daos.WordDataSource
import com.example.aikospbus.data.daos.WordLocalDataSource
import com.example.aikospbus.data.models.Word
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val localDataSource: WordDataSource,
) : WordRepository {

    override suspend fun insertWord(word: Word) {
        localDataSource.insertWord(word)
    }

    override suspend fun insertWordList(wordList: List<Word>) {
        localDataSource.insertWordList(wordList)
    }

    override suspend fun getAllWords() = localDataSource.getAllWords()

    override suspend fun updateWords(word: Word) {
        localDataSource.updateWords(word)
    }
}