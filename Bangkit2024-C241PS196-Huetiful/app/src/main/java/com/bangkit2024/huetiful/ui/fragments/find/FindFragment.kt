package com.bangkit2024.huetiful.ui.fragments.find

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.databinding.FragmentFindBinding
import com.bangkit2024.huetiful.ui.activity.result.ResultActivity

class FindFragment : Fragment() {

    private var _binding: FragmentFindBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTvTitle()
        setupAction()
    }

    private fun setupAction() {
        binding.btnAnalyzeFind.setOnClickListener {
            val intent = Intent(requireContext(), ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTvTitle() {
        val textTitle = getString(R.string.find_title)
        val textEnd = getString(R.string.home_title_end)
        val spanTextTitle = SpannableString(getString(R.string.pair))
        spanTextTitle.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.red_700)), 0, spanTextTitle.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvTitleFind.text = textTitle
        binding.tvTitleFind.append(spanTextTitle)
        binding.tvTitleFind.append(textEnd)
    }
}