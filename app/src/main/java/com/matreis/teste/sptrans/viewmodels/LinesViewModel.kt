package com.matreis.teste.sptrans.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.domain.model.Line
import com.matreis.teste.sptrans.domain.usecase.GetLineUseCase
import com.matreis.teste.sptrans.helper.CoroutineDispatcherType.*
import com.matreis.teste.sptrans.helper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.matreis.teste.sptrans.helper.Dispatcher

@HiltViewModel
class LinesViewModel @Inject constructor(
    private val getLineUseCase: GetLineUseCase,
    @Dispatcher(IO) private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _lines = MutableLiveData<List<Line>>()
    val lines: LiveData<List<Line>> get() = _lines

    private val _error = MutableLiveData<Event<Int>>()
    val error: LiveData<Event<Int>> get() = _error

    fun getLinesByTerm(term: String) {
        _isLoading.value = true
        viewModelScope.launch(coroutineDispatcher) {
            try {
                val response = getLineUseCase(term)
                if (response.isSuccessful) {
                    _lines.postValue(response.body())
                } else {
                    _error.postValue(Event(R.string.error_get_lines))
                }
            }catch (e: Exception){
                _error.postValue(Event(R.string.error_get_lines))
            }finally {
                _isLoading.postValue(false)
            }
        }
    }

}