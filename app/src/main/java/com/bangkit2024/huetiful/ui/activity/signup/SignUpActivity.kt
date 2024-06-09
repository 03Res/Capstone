package com.bangkit2024.huetiful.ui.activity.signup

import android.app.ActivityOptions
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
import com.bangkit2024.huetiful.databinding.ActivitySignUpBinding
import com.bangkit2024.huetiful.ui.ViewModelFactory.AuthViewModelFactory
import com.bangkit2024.huetiful.ui.activity.login.LoginActivity
import com.bangkit2024.huetiful.data.Result
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    private val signUpViewModel by viewModels<SignUpViewModel> {
        AuthViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This code for removing the app bar
        // Remove later if end up using app bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupAction()

    }

    private fun setupAction() {
        binding.btnSignup.setOnClickListener {
            // uncomment this line when api service is available
//            registerUser()
            navigateToLogin()
        }
        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            val option = ActivityOptions.makeCustomAnimation(this, R.anim.fade, R.anim.fade_out)
            startActivity(intent, option.toBundle())
        }
    }

    private fun registerUser() {
        val name = binding.edName.text.toString()
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()

        lifecycleScope.launch {
            signUpViewModel.register(name, email, password)
            signUpViewModel.registerState.collect { result ->
                when (result) {
                    is Result.Loading -> showLoading()
                    is Result.Success -> navigateToLogin()
                    is Result.Error -> showRegistrationError(result.error)
                }
            }
        }
    }

    private fun showRegistrationError(error: String) {
        Log.d(TAG, "registration failed with error: $error")
        showToast(getString(R.string.failed_register_account))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToLogin() {
        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
        val option = ActivityOptions.makeCustomAnimation(this, R.anim.fade, R.anim.fade_out)
        startActivity(intent, option.toBundle())
    }

    private fun showLoading() {
        binding.pbSignup.isVisible = true
    }

    companion object {
        const val TAG = "SignUpActivity"
    }
}