package com.bangkit2024.huetiful.ui.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.data.local.model.PalateModel
import com.bangkit2024.huetiful.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyData = setupDummyData()
        setupAdapter(dummyData)
    }

    private fun setupDummyData(): List<PalateModel> {
        val palates = listOf(
            PalateModel(
                color1 = "#C44F6B",
                color2 = "#FF859F",
                color3 = "#FFBCD5",
                color4 = "#F6D0C5"
            ),
            PalateModel(
                color1 = "#C44F6B",
                color2 = "#FF859F",
                color3 = "#FFBCD5",
                color4 = "#F6D0C5"
            ),
            PalateModel(
                color1 = "#C44F6B",
                color2 = "#FF859F",
                color3 = "#FFBCD5",
                color4 = "#F6D0C5"
            ),
            PalateModel(
                color1 = "#C44F6B",
                color2 = "#FF859F",
                color3 = "#FFBCD5",
                color4 = "#F6D0C5"
            ),
            PalateModel(
                color1 = "#C44F6B",
                color2 = "#FF859F",
                color3 = "#FFBCD5",
                color4 = "#F6D0C5"
            ),
            PalateModel(
                color1 = "#C44F6B",
                color2 = "#FF859F",
                color3 = "#FFBCD5",
                color4 = "#F6D0C5"
            )
        )

        return palates
    }

    private fun setupAdapter(palateModel: List<PalateModel>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorite.layoutManager = layoutManager

        val favoriteAdapter = FavoriteAdapter()
        binding.rvFavorite.adapter = favoriteAdapter
        favoriteAdapter.submitList(palateModel)
    }
}