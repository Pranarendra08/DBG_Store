package com.example.dbgstore.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dbgstore.MainActivity
import com.example.dbgstore.R
import com.example.dbgstore.databinding.ActivitySignInBinding
import com.example.dbgstore.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            btnContinueSignIn.setOnClickListener {
                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            }

            btnSignUp.setOnClickListener {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            }
        }
    }
}