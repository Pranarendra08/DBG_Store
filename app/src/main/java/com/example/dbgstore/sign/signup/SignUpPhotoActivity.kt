package com.example.dbgstore.sign.signup

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dbgstore.R
import com.example.dbgstore.databinding.ActivitySignUpPhotoBinding
import com.example.dbgstore.sign.signin.User
import com.example.dbgstore.utils.Preferences
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.util.UUID

class SignUpPhotoActivity : AppCompatActivity(), PermissionListener {

    private lateinit var binding: ActivitySignUpPhotoBinding

    val REQ_IMAGE_CAPTURE = 1
    var statusAdd:Boolean = false
    lateinit var filePath:Uri
    val imagePicker = ImagePicker

    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    lateinit var user: User
    private lateinit var mFirebaseDatabase: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {

            preferences = Preferences(this@SignUpPhotoActivity)
            storage = FirebaseStorage.getInstance()
            storageReference = storage.getReference()

            mFirebaseInstance = FirebaseDatabase.getInstance("https://dbg-store-default-rtdb.asia-southeast1.firebasedatabase.app/")
            mFirebaseDatabase = mFirebaseInstance.getReference("User")

            user = intent.getParcelableExtra("data")!!

            tvNama.text = user.nama
            tvEmail.text = user.email

            ivUpload.setOnClickListener {
                if (statusAdd) {
                    statusAdd = false
                    btnCreateAccount.visibility = View.VISIBLE
                    ivUpload.setImageResource(R.drawable.ic_upload)
                    ivProfilePhoto.setImageResource(R.drawable.shape_circle)
                } else {
//                    Dexter.withActivity(this@SignUpPhotoActivity)
//                        .withPermission(android.Manifest.permission.CAMERA)
//                        .withListener(this@SignUpPhotoActivity)
//                        .check()
                    ImagePicker.with(this@SignUpPhotoActivity)
                        .cameraOnly()
                        .start()
                }
            }

            btnCreateAccount.setOnClickListener {
                if (filePath != null) {
                    var progressDialog = ProgressDialog(this@SignUpPhotoActivity)
                    progressDialog.setTitle("Uploading...")
                    progressDialog.show()

                    var ref = storageReference.child("images/users/" + UUID.randomUUID().toString())
                    ref.putFile(filePath)
                        .addOnSuccessListener {
                            progressDialog.dismiss()
                            Toast.makeText(this@SignUpPhotoActivity, "Uploaded", Toast.LENGTH_LONG).show()

                            ref.downloadUrl.addOnSuccessListener {
                                saveToFirebase(it.toString())
                            }

                            finishAffinity()
                            startActivity(Intent(this@SignUpPhotoActivity, SignUpSuccessActivity::class.java))
                        }
                        .addOnFailureListener {
                            progressDialog.dismiss()
                            Toast.makeText(this@SignUpPhotoActivity, "Failed", Toast.LENGTH_LONG).show()
                        }
                        .addOnProgressListener {
                            taskSnapshot -> var progress = 100.0 + taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                            progressDialog.setMessage("Upload " + progress.toInt() + " %")
                        }
                }
            }
        }
    }

    private fun saveToFirebase(url: String) {
        mFirebaseDatabase.child(user.nama!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user.url = url
                mFirebaseDatabase.child(user.nama!!).setValue(user)

                preferences.setValue("nama", user.nama.toString())
                preferences.setValue("email", user.email.toString())
                preferences.setValue("url", "")
                preferences.setValue("status", "1")

                finishAffinity()
                startActivity(Intent(this@SignUpPhotoActivity, SignUpSuccessActivity::class.java))
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpPhotoActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQ_IMAGE_CAPTURE)
            }
        }
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this, "Anda tidak bisa menambahkan photo profile", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        with(binding) {
            if (resultCode == Activity.RESULT_OK) {
                statusAdd = true
                filePath = data?.data!!

                Glide.with(this@SignUpPhotoActivity)
                    .load(filePath)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivProfilePhoto)

                btnCreateAccount.visibility = View.VISIBLE
                ivUpload.visibility = View.INVISIBLE
            } else if (resultCode == imagePicker.RESULT_ERROR) {
                Toast.makeText(this@SignUpPhotoActivity, imagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@SignUpPhotoActivity, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}