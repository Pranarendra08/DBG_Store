package com.example.dbgstore.sign.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dbgstore.MainActivity
import com.example.dbgstore.databinding.ActivitySignInBinding
import com.example.dbgstore.sign.signup.SignUpActivity
import com.example.dbgstore.utils.Preferences
import com.google.firebase.database.*

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    lateinit var iUsername: String
    lateinit var iPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            mDatabase = FirebaseDatabase.getInstance("https://dbg-store-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("user")
            preferences = Preferences(this@SignInActivity)

            preferences.setValue("onboarding", "1")
            if (preferences.getValue("status").equals("1")) {
                finishAffinity()

                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            }


            btnContinueSignIn.setOnClickListener {
                iUsername = edtUsername.text.toString()
                iPassword = edtPassword.text.toString()

                if (iUsername.equals("")) {
                    edtUsername.error = "Silahkan tulis username anda"
                    edtUsername.requestFocus()
                } else if (iPassword.equals("")) {
                    edtPassword.error = "Silahkan tulis password anda"
                    edtPassword.requestFocus()
                } else {
                    pushLogin(iUsername, iPassword)
                }
                //startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            }

            btnSignUp.setOnClickListener {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
            }
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "Username tidak ditemukan",
                        Toast.LENGTH_LONG).show()
                } else {
                    if (user.password.equals(iPassword)) {
                        val preference = preferences
                        preference.setValue("nama", user.nama.toString())
                        preference.setValue("url", user.url.toString())
                        preference.setValue("email", user.email.toString())
                        preference.setValue("status", "1")

                        var intent = Intent(this@SignInActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password anda salah",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}