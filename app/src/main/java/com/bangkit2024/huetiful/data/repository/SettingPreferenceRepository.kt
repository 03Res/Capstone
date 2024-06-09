package com.bangkit2024.huetiful.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.bangkit2024.huetiful.data.pref.SettingsPreference

class SettingPreferenceRepository private constructor(
    private val settingsPreference: SettingsPreference
){

    fun getThemeSetting(): LiveData<Boolean> {
        return settingsPreference.getThemeSetting().asLiveData()
    }

    suspend fun saveThemeSetting(isDarkMode: Boolean) {
        settingsPreference.saveThemeSetting(isDarkMode)
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingPreferenceRepository? = null
        fun getInstance( settingsPreference: SettingsPreference) : SettingPreferenceRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SettingPreferenceRepository(settingsPreference)
            }.also { INSTANCE = it }
    }
}