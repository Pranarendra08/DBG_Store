package com.example.dbgstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dbgstore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        supportActionBar?.hide()
    }

    private fun initView() {
        with(binding) {
            //val navHostfragment = supportFragmentManager
        }
    }
}