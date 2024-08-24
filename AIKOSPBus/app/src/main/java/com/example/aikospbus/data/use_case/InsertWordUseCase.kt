package com.example.aikospbus.data.use_case

import com.example.aikospbus.data.models.Word
import com.example.aikospbus.data.repository.WordRepository
import javax.inject.Inject

class InsertWordUseCase @Inject constructor(private val wordRepository: WordRepository) {

    suspend operator fun invoke(word: Word) {
        wordRepository.insertWord(word)
    }
}