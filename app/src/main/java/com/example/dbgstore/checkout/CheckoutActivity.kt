package com.example.dbgstore.checkout

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.bumptech.glide.Glide
import com.example.dbgstore.R
import com.example.dbgstore.data.Games
import com.example.dbgstore.data.Nominal
import com.example.dbgstore.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private var dataList = ArrayList<Nominal>()

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews() {
        with(binding) {
            dataList = intent.getSerializableExtra("data") as ArrayList<Nominal>
            val data = intent.getParcelableExtra<Games>("datas")
            val idPlayer = intent.getStringExtra(EXTRA_ID)

            Glide.with(this@CheckoutActivity)
                .load(data?.url)
                .into(ivPhotoGame)

            tvNamaGameDetail.text = data?.nama
            tvTipeDetail.text = data?.tipe
            tvIdPlayer.text = idPlayer

            tvJumlahCheckout.text = dataList[0].jumlah
            tvHargaCheckout.text = dataList[0].harga
            tvTotalHarga.text = dataList[0].harga


            btnConfirmPayment.setOnClickListener {
                startActivity(Intent(this@CheckoutActivity, CheckoutCompleteActivity::class.java))
                showNotif(data)
            }
        }
    }

    private fun showNotif(dataNotif: Games?) {
        val NOTIFICATION_CHANNEL_ID = "channel_dbgstore_notif"
        val context = this.applicationContext
        var notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelName = "DBGSTORE NOTIF Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

//        val mIntent = Intent(this, CheckoutCompleteActivity::class.java)
//        val bundle = Bundle()
//        bundle.putParcelable("data", dataNotif)
//        mIntent.putExtra(bundle)
//
//        val pendingIntent =
//            PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        builder.setSmallIcon(R.drawable.ic_logobdgstore)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    this.resources,
                    R.drawable.ic_logobdgstore
                )
            )
            .setTicker("notif dbgstore starting")
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setLights(Color.GREEN, 3000, 3000)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setContentTitle("Top Up Sukses")
            .setContentText("Cek akun " + dataNotif?.nama + " anda!")

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(115, builder.build())

    }
}