package com.bangkit2024.huetiful.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveThemeSetting(isDarkMode: Boolean) {
        dataStore.edit { preference ->
            preference[THEME_KEY] = isDarkMode
        }
    }

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preference ->
            preference[THEME_KEY] ?: false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingsPreference? = null

        private val THEME_KEY = booleanPreferencesKey("theme_setting")

        fun getInstance(dataStore: DataStore<Preferences>): SettingsPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingsPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}