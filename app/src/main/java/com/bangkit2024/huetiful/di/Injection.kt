package com.bangkit2024.huetiful.di

import android.content.Context
import com.bangkit2024.huetiful.data.pref.SettingsPreference
import com.bangkit2024.huetiful.data.pref.UserPreference
import com.bangkit2024.huetiful.data.pref.datastore
import com.bangkit2024.huetiful.data.remote.retrofit.ApiConfig
import com.bangkit2024.huetiful.data.repository.AuthRepository
import com.bangkit2024.huetiful.data.repository.PreferenceRepository
import com.bangkit2024.huetiful.data.repository.SettingPreferenceRepository

object Injection {
    fun providePrefRepository(context: Context) : PreferenceRepository {
        val pref = UserPreference.getInstance(context.datastore)
        return PreferenceRepository.getInstance(pref)
    }

    fun provideSettingRepository(context: Context) : SettingPreferenceRepository {
        val settingPref = SettingsPreference.getInstance(context.datastore)
        return SettingPreferenceRepository.getInstance(settingPref)
    }

    fun provideAuthRepository() : AuthRepository {
        val authApiService = ApiConfig.getAuthApiService()
        return AuthRepository.getInstance(authApiService)
    }
}