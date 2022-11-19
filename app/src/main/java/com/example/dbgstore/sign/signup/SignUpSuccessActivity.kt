package com.example.dbgstore.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbgstore.R
import com.example.dbgstore.databinding.ActivitySignUpSuccessBinding
import com.example.dbgstore.onboarding.OnBoardingActivity

class SignUpSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            btnTopUp.setOnClickListener {
                startActivity(Intent(this@SignUpSuccessActivity, OnBoardingActivity::class.java))
            }
        }
    }
}