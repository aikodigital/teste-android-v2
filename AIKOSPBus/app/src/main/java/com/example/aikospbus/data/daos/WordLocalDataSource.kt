package com.example.aikospbus.data.daos

import com.example.aikospbus.data.models.Word

class WordLocalDataSource(private val wordDao: WordDao) : WordDataSource {
    override suspend fun insertWord(word: Word) {
       return wordDao.insertWord(word)
    }

    override suspend fun insertWordList(wordList: List<Word>) {
        return wordDao.insertWordList(wordList)
    }

    override suspend fun getAllWords(): List<Word> {
        return wordDao.getAllWords()
    }

    override suspend fun updateWords(word: Word) {
        return wordDao.updateWords(word)
    }
}