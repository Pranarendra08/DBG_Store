package com.example.dbgstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.dbgstore.databinding.ActivityMainBinding
import com.example.dbgstore.fragment.home.HomeFragment
import com.example.dbgstore.fragment.profile.ProfileFragment

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
            val fragmentHome = HomeFragment()
            val fragmentProfile = ProfileFragment()

            setFragment(fragmentHome)

            ivHome.setOnClickListener {
                setFragment(fragmentHome)

                chageIcon(ivHome, R.drawable.ic_home_active)
                chageIcon(ivProfile, R.drawable.ic_profile)
            }

            ivProfile.setOnClickListener {
                setFragment(fragmentProfile)

                chageIcon(ivHome, R.drawable.ic_home)
                chageIcon(ivProfile, R.drawable.ic_profile_active)
            }
        }
    }

    private fun chageIcon(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_home, fragment)
        fragmentTransaction.commit()
    }


}