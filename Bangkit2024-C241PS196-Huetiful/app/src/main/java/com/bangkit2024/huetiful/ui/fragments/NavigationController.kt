package com.bangkit2024.huetiful.ui.fragments

import android.view.View
import androidx.navigation.Navigation.findNavController
import com.bangkit2024.huetiful.R

class NavigationController() {
    fun navigatToFullDetailFragment(view: View) {
        val navController = findNavController(view)
        navController.navigate(R.id.action_navigation_result_to_navigation_fulldetail)
    }

    fun navigateToDetailFragment(view: View) {
        val navController = findNavController(view)
        navController.navigate(R.id.action_navigation_result_to_navigation_detail)
    }

    fun navigateToResultFragment(view: View) {
        val navController = findNavController(view)
        navController.navigate(R.id.action_navigation_detail_to_navigation_result)
    }

    fun navigateToFullDetailFragmentFromDetail(view: View) {
        val navController = findNavController(view)
        navController.navigate(R.id.action_navigation_detail_to_navigation_fulldetail)
    }

    companion object {
        private val instance = NavigationController()
        fun getInstance() = instance
    }
}