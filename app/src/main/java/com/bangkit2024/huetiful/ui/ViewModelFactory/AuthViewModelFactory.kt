package com.bangkit2024.huetiful.ui.ViewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit2024.huetiful.data.pref.SettingsPreference
import com.bangkit2024.huetiful.data.repository.AuthRepository
import com.bangkit2024.huetiful.data.repository.PreferenceRepository
import com.bangkit2024.huetiful.data.repository.SettingPreferenceRepository
import com.bangkit2024.huetiful.di.Injection
import com.bangkit2024.huetiful.ui.activity.login.LoginViewModel
import com.bangkit2024.huetiful.ui.activity.signup.SignUpViewModel
import com.bangkit2024.huetiful.ui.activity.welcome.WelcomeViewModel
import com.bangkit2024.huetiful.ui.fragments.settings.SettingsViewModel

class AuthViewModelFactory(
    private val authRepository: AuthRepository,
    private val preferenceRepository: PreferenceRepository,
    private val settingsPreferenceRepository: SettingPreferenceRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository, preferenceRepository) as T
            }
            modelClass.isAssignableFrom(WelcomeViewModel::class.java) -> {
                WelcomeViewModel(preferenceRepository, settingsPreferenceRepository) as T
            }
            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> {
                SettingsViewModel(preferenceRepository, settingsPreferenceRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
        }
    }

    companion object {
        private var INSTANCE: AuthViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context) : AuthViewModelFactory {
            val authRepository = Injection.provideAuthRepository()
            val preferenceRepository = Injection.providePrefRepository(context)
            val settingsPreferenceRepository = Injection.provideSettingRepository(context)
            if (INSTANCE == null) {
                synchronized(AuthViewModelFactory::class.java) {
                    INSTANCE = AuthViewModelFactory(authRepository, preferenceRepository, settingsPreferenceRepository)
                }
            }
            return INSTANCE as AuthViewModelFactory
        }
    }
}