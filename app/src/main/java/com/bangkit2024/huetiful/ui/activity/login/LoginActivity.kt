package com.bangkit2024.huetiful.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.data.Result
import com.bangkit2024.huetiful.databinding.ActivityLoginBinding
import com.bangkit2024.huetiful.ui.ViewModelFactory.AuthViewModelFactory
import com.bangkit2024.huetiful.ui.activity.main.MainActivity
import com.bangkit2024.huetiful.ui.activity.signup.SignUpActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> {
        AuthViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This code for removing the app bar
        // Remove later if end up using app bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupAction()
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            // uncomment this line whe api service available
//            userLogin()
            navigateToHome()
        }
        binding.tvToSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun userLogin() {
        val email = binding.edEmailLogin.text.toString()
        val password = binding.edPasswordLogin.text.toString()

        lifecycleScope.launch {
            loginViewModel.login(email, password)
            loginViewModel.loginState.collect { result ->
                when (result) {
                    is Result.Loading -> showLoading()
                    is Result.Success -> navigateToHome()
                    is Result.Error -> showLoginError(result.error)
                }
            }
        }
    }

    private fun showLoginError(error: String) {
        Log.d(TAG, "registration failed with error: $error")
        showToast(getString(R.string.failed_register_account))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToHome() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showLoading() {
        binding.pbLogin.isVisible = true
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}