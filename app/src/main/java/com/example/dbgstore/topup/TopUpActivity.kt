package com.example.dbgstore.topup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dbgstore.R
import com.example.dbgstore.checkout.CheckoutActivity
import com.example.dbgstore.databinding.ActivityTopUpBinding

class TopUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            Glide.with(this@TopUpActivity)
                .load(R.drawable.photo_profile)
                .apply(RequestOptions.circleCropTransform())
                .into(ivPhotoTopup)

            btnContinueTopUp.setOnClickListener {
                startActivity(Intent(this@TopUpActivity, CheckoutActivity::class.java))
            }
        }
    }
}