package com.example.dbgstore.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dbgstore.databinding.ActivitySignUpBinding
import com.example.dbgstore.sign.signin.SignInActivity
import com.example.dbgstore.sign.signin.User
import com.example.dbgstore.utils.Preferences
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    lateinit var sNama: String
    lateinit var sEmail: String
    lateinit var sPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            mFirebaseInstance = FirebaseDatabase.getInstance("https://dbg-store-default-rtdb.asia-southeast1.firebasedatabase.app/")
            mDatabase = mFirebaseInstance.getReference()
            mDatabaseReference = mFirebaseInstance.getReference("User")

            preferences = Preferences(this@SignUpActivity)

            btnContinue.setOnClickListener {
                sNama = edtFullname.text.toString()
                sEmail = edtEmail.text.toString()
                sPassword = edtPassword.text.toString()

                if (sNama.equals("")) {
                    edtFullname.error = "Silahkan isi nama lengkap anda"
                    edtFullname.requestFocus()
                } else if (sEmail.equals("")) {
                    edtEmail.error = "Silahkan isi email anda"
                    edtEmail.requestFocus()
                } else if (sPassword.equals("")) {
                    edtEmail.error = "Silahkan isi password anda"
                    edtEmail.requestFocus()
                } else {
                    var statusName = sNama.indexOf(".")
                    if (statusName >= 0) {
                        edtFullname.error = "Silahkan tulis nama Anda tanpa ."
                        edtFullname.requestFocus()
                    } else {
                        saveUser(sNama, sEmail, sPassword)
                    }
                }
            }
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
            }
        }
    }

    private fun saveUser(sNama: String, sEmail: String, sPassword: String) {
        var user = User()
        user.nama = sNama
        user.email = sEmail
        user.password = sPassword
        checkUser(sNama, user)
    }

    private fun checkUser(sNama: String, data: User) {
        mDatabaseReference.child(sNama).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(sNama).setValue(data)

                    preferences.setValue("nama", data.nama.toString())
                    preferences.setValue("email", data.email.toString())
                    preferences.setValue("url", "")
                    preferences.setValue("status", "1")

                    startActivity(Intent(this@SignUpActivity, SignUpPhotoActivity::class.java).putExtra("data", data))
                }else {
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, "" + error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}