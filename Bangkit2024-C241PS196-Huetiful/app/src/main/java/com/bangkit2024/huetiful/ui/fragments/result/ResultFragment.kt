package com.bangkit2024.huetiful.ui.fragments.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit2024.huetiful.data.local.model.PalateModel
import com.bangkit2024.huetiful.databinding.FragmentResultBinding
import com.bangkit2024.huetiful.ui.fragments.NavigationController

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val navigationController = NavigationController.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyData = setupDummyData()
        setupAdapter(dummyData)
        setupAction()
    }

    private fun setupAction() {
        binding.tvShowMore.setOnClickListener {
            navigationController.navigateToDetailFragment(it)
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
        binding.rvResult.layoutManager = layoutManager

        val resultAdapter = ResultAdapter(navigationController, this)
        binding.rvResult.adapter = resultAdapter
        resultAdapter.submitList(palateModel)
    }

}