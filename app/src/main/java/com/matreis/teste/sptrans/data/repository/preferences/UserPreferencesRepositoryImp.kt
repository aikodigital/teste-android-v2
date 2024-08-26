package com.matreis.teste.sptrans.data.repository.preferences

import com.matreis.teste.sptrans.data.preferences.UserPreferences
import javax.inject.Inject

class UserPreferencesRepositoryImp @Inject constructor(
    private val userPreferences: UserPreferences
): UserPreferencesRepository {

}
