package com.matreis.teste.sptrans.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
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
    }

    override suspend fun getCookie(): String {
        return context.dataStore.data.first()[COOKIE] ?: ""
    }

    override suspend fun setCookie(cookie: String) {
        context.dataStore.edit { settings ->
            settings[COOKIE] = cookie
        }
    }

}