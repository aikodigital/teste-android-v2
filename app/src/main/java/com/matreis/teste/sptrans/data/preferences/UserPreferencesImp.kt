package com.matreis.teste.sptrans.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserPreferencesImp @Inject constructor(
    private val context: Context
): UserPreferences {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userPreferences")
    companion object {
        private val COOKIE = stringPreferencesKey("cookie")
        private val AUTO_UPDATE = booleanPreferencesKey("auto_update")
        private val AUTO_UPDATE_INTERVAL = intPreferencesKey("auto_update_interval")

    }

    override suspend fun getCookie(): String {
        return context.dataStore.data.first()[COOKIE] ?: ""
    }

    override suspend fun setCookie(cookie: String) {
        context.dataStore.edit { settings ->
            settings[COOKIE] = cookie
        }
    }

    override suspend fun setAutoUpdate(autoUpdate: Boolean) {
        context.dataStore.edit { settings ->
            settings[AUTO_UPDATE] = autoUpdate
        }
    }

    override suspend fun getAutoUpdate(): Boolean {
        return context.dataStore.data.first()[AUTO_UPDATE] ?: false
    }

    override suspend fun setAutoUpdateInterval(interval: Int) {
        context.dataStore.edit { settings ->
            settings[AUTO_UPDATE_INTERVAL] = interval
        }
    }

    override suspend fun getAutoUpdateInterval(): Int {
        return context.dataStore.data.first()[AUTO_UPDATE_INTERVAL] ?: 0
    }

}