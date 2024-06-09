package com.bangkit2024.huetiful.ui.activity.main

import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.databinding.ActivityMainBinding
import com.bangkit2024.huetiful.ui.ViewModelFactory.AuthViewModelFactory
import com.bangkit2024.huetiful.ui.fragments.settings.SettingsViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val settingsViewModel by viewModels<SettingsViewModel> {
        AuthViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navHostMain

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_find,
                R.id.navigation_favorite,
                R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        userLogout()
    }

    private fun userLogout() {
        settingsViewModel.closeApp.observe(this) { shouldClose ->
            if (shouldClose) {
                finish()
            }
        }
    }
}