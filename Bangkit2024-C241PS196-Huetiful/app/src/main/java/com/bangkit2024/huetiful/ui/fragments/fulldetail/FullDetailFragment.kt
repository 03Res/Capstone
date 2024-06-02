package com.bangkit2024.huetiful.ui.fragments.fulldetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.data.local.model.DetailPalateModel
import com.bangkit2024.huetiful.databinding.FragmentFullDetailBinding

class FullDetailFragment : Fragment() {

    private var _binding : FragmentFullDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyData = setupDummyData()
        sutupAdapter(dummyData)
    }

    private fun sutupAdapter(data: List<DetailPalateModel>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFullDetail.layoutManager = layoutManager

        val fullDetailAdapter = FullDetailAdapter()
        binding.rvFullDetail.adapter = fullDetailAdapter
        fullDetailAdapter.submitList(data)
    }

    private fun setupDummyData(): List<DetailPalateModel> {
        return listOf(
            DetailPalateModel(
                color = "#C44F6B"
            ),
            DetailPalateModel(
                color = "#FF859F"
            ),
            DetailPalateModel(
                color = "#FFBCD5"
            ),
            DetailPalateModel(
                color = "#F6D0C5"
            )
        )
    }
}