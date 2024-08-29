package br.com.aiko.projetoolhovivo.ui.line

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aiko.projetoolhovivo.data.model.line.Line
import br.com.aiko.projetoolhovivo.domain.usecase.line.LineUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LineViewModel @Inject constructor(
    private val lineUseCase: LineUseCase
) : ViewModel() {
    var token: String = ""

    private var _isLoading = MutableLiveData(true)
    private val _lines = MutableLiveData<List<Line>>(listOf())
    private val _error = MutableLiveData<String?>(null)

    val isLoading: LiveData<Boolean> get() = _isLoading
    val lines: LiveData<List<Line>> get() = _lines
    val error: LiveData<String?> get() = _error

    fun getLines() = viewModelScope.launch {
        _isLoading.postValue(true)
        lineUseCase.getLines(token).onSuccess { lines ->
            _lines.postValue(lines)
            _isLoading.postValue(false)
        }.onFailure { fail ->
            run {
                _isLoading.postValue(false)
                _error.postValue(fail.stackTraceToString())
            }
        }
    }
}