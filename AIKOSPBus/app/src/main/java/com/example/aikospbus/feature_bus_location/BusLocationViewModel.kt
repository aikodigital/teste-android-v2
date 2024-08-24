package com.example.aikospbus.feature_bus_location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aikospbus.data.models.Word
import com.example.aikospbus.data.use_case.InsertWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusLocationViewModel @Inject constructor(
    private val insertWordUseCase: InsertWordUseCase
): ViewModel() {


    fun insertWord(word: Word) = viewModelScope.launch {
        insertWordUseCase(word)
    }
}