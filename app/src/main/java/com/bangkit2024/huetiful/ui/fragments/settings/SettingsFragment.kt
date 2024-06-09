package com.bangkit2024.huetiful.ui.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bangkit2024.huetiful.databinding.FragmentSettingsBinding
import com.bangkit2024.huetiful.ui.ViewModelFactory.AuthViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val settingsViewModel by viewModels<SettingsViewModel> {
        AuthViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSwitchStatus()
        setupAction()
    }

    private fun observeSwitchStatus() {
        settingsViewModel.getThemeSetting().observe(requireActivity()) { isDarkMode: Boolean ->
            if (isDarkMode) {
                binding.swChangeTheme.isChecked = true
            } else {
                binding.swChangeTheme.isChecked = false
            }
        }
    }

    private var debounceJob: Job? = null

    private fun setupAction() {
        binding.swChangeTheme.setOnCheckedChangeListener { _, isChecked ->
            debounceJob?.cancel()
            debounceJob = viewLifecycleOwner.lifecycleScope.launch {
                delay(500) // Debounce delay of 500 milliseconds
                updateTheme(isChecked)
            }
        }
    }

    private fun updateTheme(isChecked: Boolean) {
        settingsViewModel.saveThemeSetting(isChecked)
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.swChangeTheme.isChecked = true
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.swChangeTheme.isChecked = false
        }
    }
}