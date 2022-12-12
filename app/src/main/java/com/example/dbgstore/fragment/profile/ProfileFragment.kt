package com.example.dbgstore.fragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dbgstore.databinding.FragmentProfileBinding
import com.example.dbgstore.sign.signin.SignInActivity
import com.example.dbgstore.utils.Preferences

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        if (activity != null && context != null) {
            with(binding) {
                preferences = Preferences(requireContext())

                tvNamaProfile.text = preferences.getValue("nama")
                tvEmailProfile.text = preferences.getValue("email")

                Glide.with(this@ProfileFragment)
                    .load(preferences.getValue("url"))
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivPhotoProfile)

                btnLogout.setOnClickListener {
                    Toast.makeText(context, "Fitur belum tersedia", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}