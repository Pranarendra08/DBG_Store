package com.example.dbgstore.topup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dbgstore.checkout.CheckoutActivity
import com.example.dbgstore.data.Games
import com.example.dbgstore.data.Nominal
import com.example.dbgstore.databinding.ActivityTopUpBinding
import com.example.dbgstore.utils.Preferences
import com.google.firebase.database.*

class TopUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopUpBinding
    private var dataList = ArrayList<Nominal>()
    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    var statusCard1: Boolean = false
    var statusCard2: Boolean = false
    var statusCard3: Boolean = false
    var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            val data = intent.getParcelableExtra<Games>("data")

            mDatabase = FirebaseDatabase.getInstance("https://dbg-store-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("game")
                .child(data!!.nama.toString())

            Glide.with(this@TopUpActivity)
                .load(data?.url.toString())
                .apply(RequestOptions.centerCropTransform())
                .into(ivPhotoGameDetails)

            tvNamaGameDetail.text = data?.nama
            tvTipeDetail.text = data?.tipe

            var isEmpty = false
            if (edtVerifyId.text.trim().contentEquals("")) {
                isEmpty = true
                edtVerifyId.error = "Silahkan isi ID game anda"
                edtVerifyId.requestFocus()
            }

            tvJumlah1.text = data.jumlah1
            tvJumlah2.text = data.jumlah2
            tvJumlah3.text = data.jumlah3

            tvHarga1.text = data.harga1
            tvHarga2.text = data.harga2
            tvHarga3.text = data.harga3

            card1.setOnClickListener {
                if (statusCard1) {
                    statusCard1 = false
                    total -= 1
                    changeCardBackground(card1, statusCard1)

                    dataList.remove(Nominal(data.harga1, data.jumlah1))
                } else {
                    statusCard1 = true
                    total += 1
                    changeCardBackground(card1, statusCard1)

                    val dataTopup = Nominal(data.harga1, data.jumlah1)
                    dataList.add(dataTopup)
                }
            }

            card2.setOnClickListener {
                if (statusCard2) {
                    statusCard2 = false
                    total -= 1
                    changeCardBackground(card2, statusCard2)

                    dataList.remove(Nominal(data.harga2, data.jumlah2))
                } else {
                    statusCard2 = true
                    total += 1
                    changeCardBackground(card2, statusCard2)

                    val dataTopup = Nominal(data.harga2, data.jumlah2)
                    dataList.add(dataTopup)
                }
            }

            card3.setOnClickListener {
                if (statusCard3) {
                    statusCard3 = false
                    total -= 1
                    changeCardBackground(card3, statusCard3)

                    dataList.remove(Nominal(data.harga3, data.jumlah3))
                } else {
                    statusCard3 = true
                    total += 1
                    changeCardBackground(card3, statusCard3)

                    val dataTopup = Nominal(data.harga3, data.jumlah3)
                    dataList.add(dataTopup)
                }
            }

            btnContinueTopUp.setOnClickListener {
                if (total == 0 && isEmpty) {
                   Toast.makeText(this@TopUpActivity, "Pilih jumlah top up terlebih dahulu", Toast.LENGTH_SHORT).show()
                } else {
                    val playerId = edtVerifyId.text.toString()
                    startActivity(
                        Intent(this@TopUpActivity, CheckoutActivity::class.java)
                            .putExtra("data", dataList).putExtra("datas", data).putExtra(CheckoutActivity.EXTRA_ID, playerId)
                    )
                }
            }
        }
    }

    private fun changeCardBackground(card: CardView, statusCard: Boolean) {
        with(binding) {
            if (!statusCard) {
                card.setBackgroundResource(com.example.dbgstore.R.drawable.shape_rectangle_3)
            } else {
                card.setBackgroundResource(com.example.dbgstore.R.drawable.shape_rectangle_topup_checked)
            }
        }
    }
}