package com.bangkit2024.huetiful.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val datastore: DataStore<Preferences>) {
    suspend fun saveSession(user: UserModel) {
        datastore.edit { preference ->
            preference[TOKEN_KEY] = user.token
            preference[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return datastore.data.map { preference ->
            UserModel(
                preference[TOKEN_KEY] ?: "",
                preference[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun logout() {
        datastore.edit { preference ->
            preference.clear()
        }
    }

    companion object {
        private var INSTANCE: UserPreference? = null

        private var TOKEN_KEY = stringPreferencesKey("token")
        private var IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
        private var IS_DARK_MODE = booleanPreferencesKey("isDarkModeActive")

        fun getInstance(datastore: DataStore<Preferences>) : UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(datastore)
                INSTANCE = instance
                instance
            }
        }
    }
}