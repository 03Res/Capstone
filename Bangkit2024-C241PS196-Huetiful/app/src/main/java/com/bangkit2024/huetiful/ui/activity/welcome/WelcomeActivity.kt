package com.bangkit2024.huetiful.ui.activity.welcome

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bangkit2024.huetiful.R
import com.bangkit2024.huetiful.databinding.ActivityWelcomeBinding
import com.bangkit2024.huetiful.ui.activity.signup.SignUpActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSpanTvQuote()
        setupAnimation()
        setupAction()
    }

    private fun setSpanTvQuote() {
        val text = getString(R.string.welcome_quote)
        val textSpan = SpannableString("dif")
        val textSpan2 = SpannableString("fer")
        val textSpan3 = SpannableString("e")
        val textSpan4 = SpannableString("nt")
        val textEnd = ","
        textSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.red_600)), 0, textSpan.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textSpan2.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.red_gray_100)), 0, textSpan2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textSpan3.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.yellow_light)), 0, textSpan3.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textSpan4.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue_ocean)), 0, textSpan4.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvQuote1.text = text
        binding.tvQuote1.append(textSpan)
        binding.tvQuote1.append(textSpan2)
        binding.tvQuote1.append(textSpan3)
        binding.tvQuote1.append(textSpan4)
        binding.tvQuote1.append(textEnd)

        val text2 = getString(R.string.welcome_quote2)
        val text2Span = SpannableString("co")
        val text2Span2 = SpannableString("lor")
        val text2Span3 = SpannableString("ful")
        text2Span.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.red_600)), 0, text2Span.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text2Span2.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.red_gray_100)), 0, text2Span2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        text2Span3.setSpan(ForegroundColorSpan(ContextCompat.getColor(this, R.color.blue_ocean)), 0, text2Span3.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvQuote2.text = text2
        binding.tvQuote2.append(text2Span)
        binding.tvQuote2.append(text2Span2)
        binding.tvQuote2.append(text2Span3)
    }

    override fun onResume() {
        super.onResume()
        setupAction()
    }

    private fun setupAction() {
        binding.btnStart.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, SignUpActivity::class.java)
            startActivity(intent)

//            val animButton = binding.btnStart.background as AnimationDrawable
//            animButton.start()
        }
    }

    private fun setupAnimation() {
        binding.ivLogo.setImageResource(R.drawable.avd_logo)
        val animLogo = binding.ivLogo.drawable as AnimatedVectorDrawable
        animLogo.start()

//        val animButton = binding.btnStart.background as AnimationDrawable
//        animButton.start()
    }
}