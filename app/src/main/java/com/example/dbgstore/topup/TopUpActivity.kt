package com.example.dbgstore.topup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbgstore.R

class TopUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        supportActionBar?.hide()
    }
}