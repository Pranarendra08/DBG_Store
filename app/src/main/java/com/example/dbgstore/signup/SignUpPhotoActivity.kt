package com.example.dbgstore.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbgstore.R
import com.example.dbgstore.databinding.ActivitySignUpPhotoBinding

class SignUpPhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            btnCreateAccount.setOnClickListener {
                startActivity(Intent(this@SignUpPhotoActivity, SignUpSuccessActivity::class.java))
            }
        }
    }
}