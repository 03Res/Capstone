package com.bangkit2024.huetiful.ui.activity.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit2024.huetiful.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.bangkit2024.huetiful.data.Result
import com.bangkit2024.huetiful.data.pref.UserModel
import com.bangkit2024.huetiful.data.repository.PreferenceRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<String>>(Result.Loading)
    val loginState: StateFlow<Result<String>> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Result.Loading
            try {
                val loginResponse = authRepository.login(email, password)
                if (loginResponse.status == "success") {
                    val user = UserModel(
                        token = loginResponse.token,
                        isLogin = true
                    )
                    saveSession(user)
                    _loginState.emit(Result.Success(loginResponse.message))
                } else {
                    _loginState.emit(Result.Error(loginResponse.message))
                }
            } catch (e: Exception) {
                Log.d(TAG, "login failed with error : ${e.message}")
                _loginState.emit(Result.Error(e.message.toString()))
            }
        }
    }

    private fun saveSession(user: UserModel) {
        viewModelScope.launch {
            preferenceRepository.saveSession(user)
        }
    }

    companion object {
        const val TAG = "LoginViewModel"
    }
}