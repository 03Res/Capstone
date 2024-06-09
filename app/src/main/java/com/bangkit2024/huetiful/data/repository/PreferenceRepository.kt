package com.bangkit2024.huetiful.data.repository

import com.bangkit2024.huetiful.data.pref.SettingsPreference
import com.bangkit2024.huetiful.data.pref.UserModel
import com.bangkit2024.huetiful.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class PreferenceRepository private constructor(
    private val userPreference: UserPreference
) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var INSTANCE: PreferenceRepository? = null
        fun getInstance( userPreference: UserPreference) : PreferenceRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: PreferenceRepository(userPreference)
            }.also { INSTANCE = it }
    }
}