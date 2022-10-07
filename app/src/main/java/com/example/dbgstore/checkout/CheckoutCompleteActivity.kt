package com.example.dbgstore.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbgstore.MainActivity
import com.example.dbgstore.R
import com.example.dbgstore.databinding.ActivityCheckoutCompleteBinding

class CheckoutCompleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        supportActionBar?.hide()
    }

    private fun initView() {
        with(binding) {
            btnMyDashboard.setOnClickListener {
                startActivity(Intent(this@CheckoutCompleteActivity, MainActivity::class.java))
            }
        }
    }
}