package com.matreis.teste.sptrans.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matreis.teste.sptrans.data.preferences.UserPreferences
import com.matreis.teste.sptrans.helper.CoroutineDispatcherType.IO
import com.matreis.teste.sptrans.helper.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    @Dispatcher(IO) private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _isAutoUpdateEnabled = MutableLiveData<Boolean>()
    val isAutoUpdateEnabled: LiveData<Boolean> get() = _isAutoUpdateEnabled

    private val _autoUpdateInterval = MutableLiveData<Int>()
    val autoUpdateInterval: LiveData<Int> get() = _autoUpdateInterval

    fun setAutoUpdateEnabled(enabled: Boolean) {
        viewModelScope.launch(coroutineDispatcher) {
            userPreferences.setAutoUpdate(enabled)
            //_isAutoUpdateEnabled.postValue(enabled)
        }
    }

    fun setAutoUpdateInterval(interval: Int) {
        viewModelScope.launch(coroutineDispatcher) {
            userPreferences.setAutoUpdateInterval(interval)
        }
    }

    fun getConfigs() {
        viewModelScope.launch(coroutineDispatcher) {
            val isAutoUpdateEnabled = userPreferences.getAutoUpdate()
            val autoUpdateInterval = userPreferences.getAutoUpdateInterval()
            _isAutoUpdateEnabled.postValue(isAutoUpdateEnabled)
            _autoUpdateInterval.postValue(autoUpdateInterval)
        }
    }
}