package com.example.dbgstore.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.dbgstore.R
import com.example.dbgstore.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            btnConfirmPayment.setOnClickListener {
                startActivity(Intent(this@CheckoutActivity, CheckoutCompleteActivity::class.java))
            }
        }
    }
}