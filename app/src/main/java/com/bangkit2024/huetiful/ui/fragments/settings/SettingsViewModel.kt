package com.bangkit2024.huetiful.ui.fragments.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit2024.huetiful.data.repository.PreferenceRepository
import com.bangkit2024.huetiful.data.repository.SettingPreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val preferenceRepository: PreferenceRepository,
    private val settingPreferenceRepository: SettingPreferenceRepository
) : ViewModel() {

    private val _closeApp = MutableLiveData<Boolean>()
    val closeApp: LiveData<Boolean> = _closeApp

    private val _isDarkModeActive = MutableLiveData<Boolean>()
    val isDarkModeActive: LiveData<Boolean> = _isDarkModeActive

    fun logout() {
        viewModelScope.launch {
            preferenceRepository.logout()
        }
        _closeApp.value = true
    }

    fun getThemeSetting(): LiveData<Boolean> {
        return settingPreferenceRepository.getThemeSetting()
    }

    fun saveThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch {
            settingPreferenceRepository.saveThemeSetting(isDarkMode)
        }
    }
}