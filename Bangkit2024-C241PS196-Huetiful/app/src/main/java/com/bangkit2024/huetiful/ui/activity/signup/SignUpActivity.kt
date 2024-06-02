package com.bangkit2024.huetiful.ui.activity.signup

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.bangkit2024.huetiful.databinding.ActivitySignUpBinding
import com.bangkit2024.huetiful.ui.activity.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This code for removing the app bar
        // Remove later if end up using app bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // This code only for navigation testing purpose
        // Delete this later
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}