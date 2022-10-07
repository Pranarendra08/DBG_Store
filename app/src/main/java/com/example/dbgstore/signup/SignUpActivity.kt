package com.example.dbgstore.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbgstore.R
import com.example.dbgstore.databinding.ActivitySignUpBinding
import com.example.dbgstore.onboarding.OnBoardingActivity
import com.example.dbgstore.signin.SignInActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            btnContinue.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, SignUpPhotoActivity::class.java))
            }
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
            }
        }
    }
}