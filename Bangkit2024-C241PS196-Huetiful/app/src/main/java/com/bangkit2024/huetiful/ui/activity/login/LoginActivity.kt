package com.bangkit2024.huetiful.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.bangkit2024.huetiful.databinding.ActivityLoginBinding
import com.bangkit2024.huetiful.ui.activity.main.MainActivity
import com.bangkit2024.huetiful.ui.activity.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This code for removing the app bar
        // Remove later if end up using app bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // This code only for navigation testing purpose
        // Delete this later
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        binding.tvToSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}