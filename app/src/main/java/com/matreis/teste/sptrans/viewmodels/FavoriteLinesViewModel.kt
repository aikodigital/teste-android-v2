package com.matreis.teste.sptrans.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matreis.teste.sptrans.domain.usecase.DeleteFavoriteLineUseCase
import com.matreis.teste.sptrans.domain.usecase.GetFavoritesLinesUseCase
import com.matreis.teste.sptrans.helper.CoroutineDispatcherType
import com.matreis.teste.sptrans.helper.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteLinesViewModel @Inject constructor(
    private val getFavoritesLinesUseCase: GetFavoritesLinesUseCase,
    private val deleteFavoriteLineUseCase: DeleteFavoriteLineUseCase,
    @Dispatcher(CoroutineDispatcherType.IO) private val dispatcher: CoroutineDispatcher
): ViewModel() {
    fun getFavoritesLines() = getFavoritesLinesUseCase()
    fun deleteFavoriteLine(lineCode: Long) = viewModelScope.launch(dispatcher) { deleteFavoriteLineUseCase(lineCode) }
}