package com.bangkit2024.huetiful.ui.activity.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit2024.huetiful.data.repository.AuthRepository
import com.bangkit2024.huetiful.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow<Result<String>>(Result.Loading)
    val registerState: StateFlow<Result<String>> = _registerState

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = Result.Loading
            try {
                val registerResponse = authRepository.register(name, email, password)
                if (registerResponse.status == "success") {
                    _registerState.emit(Result.Success(registerResponse.message))
                } else {
                    _registerState.emit(Result.Error(registerResponse.message))
                }
            } catch (e: Exception) {
                Log.d(TAG, "register failed with exception: ${e.message}")
                _registerState.emit(Result.Error(e.message.toString()))
            }
        }
    }

    companion object {
        const val TAG = "SignUpViewModel"
    }
}