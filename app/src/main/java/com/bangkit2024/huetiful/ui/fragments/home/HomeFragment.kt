package com.bangkit2024.huetiful.ui.fragments.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.databinding.FragmentHomeBinding
import com.bangkit2024.huetiful.ui.activity.result.ResultActivity
import com.bangkit2024.huetiful.ui.utils.getImageUri
import com.yalantis.ucrop.UCrop

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private var destinationUri: Uri? = null

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            currentImageUri = data?.let { UCrop.getOutput(it) }
            showImage()
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = data?.let { UCrop.getError(it) }
            makeToast(cropError?.message.toString())
        }
    }

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTvTitle()
        setupAction()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAction() {
        binding.btnGallery.setOnClickListener {
            openGallery()
        }
        binding.btnCamera.setOnClickListener {
            openCamera()
        }
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

    private fun openGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun openCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private fun makeToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showImage() {
        currentImageUri.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivPreviewImage.setImageURI(it)
        }
    }

    private fun cropImage() {
        destinationUri = getImageUri(requireContext())
        currentImageUri.let {
            if (it != null) {
                UCrop.of(it, destinationUri!!)
                    .start(requireActivity())
            } else {
                makeToast(getString(R.string.cannot_find_image))
            }
        }
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            cropImage()
            showImage()
        } else {
            Log.d("Photo picker", "No media selected")
            makeToast(getString(R.string.no_media_selected))
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            cropImage()
            showImage()
        } else {
            makeToast(getString(R.string.error_opening_camera))
        }
    }

}