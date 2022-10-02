package com.example.dbgstore.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbgstore.MainActivity
import com.example.dbgstore.R
import com.example.dbgstore.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_on_boarding)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            btnGetStarted.setOnClickListener {
                startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
            }


        }
    }
}