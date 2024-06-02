package com.bangkit2024.huetiful.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.databinding.FragmentHomeBinding
import com.bangkit2024.huetiful.ui.activity.result.ResultActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        setTvTitle()
        setupAction()
        return binding.root
    }

    private fun setupAction() {
        binding.btnAnalyze.setOnClickListener {
            val intent = Intent(requireContext(), ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTvTitle() {
        val textTitle = getString(R.string.home_title)
        val textEnd = getString(R.string.home_title_end)
        val spanTextTitle = SpannableString(getString(R.string.tone))
        spanTextTitle.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.red_700)), 0, spanTextTitle.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvTitle.text = textTitle
        binding.tvTitle.append(spanTextTitle)
        binding.tvTitle.append(textEnd)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}