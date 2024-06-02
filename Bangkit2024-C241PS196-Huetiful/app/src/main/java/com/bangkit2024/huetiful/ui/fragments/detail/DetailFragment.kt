package com.bangkit2024.huetiful.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.data.local.model.PalateModel
import com.bangkit2024.huetiful.databinding.FragmentDetailBinding
import com.bangkit2024.huetiful.ui.fragments.NavigationController
import com.bangkit2024.huetiful.ui.fragments.result.ResultAdapter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val navigationController = NavigationController.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyData = setupDummyData()
        setupAdapter(dummyData)
        setupAction()
    }

    private fun setupAction() {
        binding.tvShowLess.setOnClickListener {
            navigationController.navigateToResultFragment(it)
        }
    }

    private fun setupDummyData(): List<PalateModel> {
        return listOf(
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
    }

    private fun setupAdapter(palateModel: List<PalateModel>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDetail.layoutManager = layoutManager

        val detailAdapter = ResultAdapter(navigationController, this)
        binding.rvDetail.adapter = detailAdapter
        detailAdapter.submitList(palateModel)
    }
}