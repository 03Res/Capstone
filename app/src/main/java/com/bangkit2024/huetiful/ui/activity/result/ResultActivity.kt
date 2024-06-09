package com.bangkit2024.huetiful.ui.activity.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.databinding.ActivityResultBinding
import com.bangkit2024.huetiful.ui.fragments.result.ResultFragment

class ResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

    }
}