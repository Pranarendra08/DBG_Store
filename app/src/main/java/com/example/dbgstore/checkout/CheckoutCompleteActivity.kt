package com.example.dbgstore.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
                finishAffinity()
                startActivity(Intent(this@CheckoutCompleteActivity, MainActivity::class.java))
            }
            btnWaAdmin.setOnClickListener {
                Toast.makeText(this@CheckoutCompleteActivity, "Fitur Belum Tersedia", Toast.LENGTH_LONG).show()
            }
        }
    }
}