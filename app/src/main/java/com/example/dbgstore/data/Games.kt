package com.example.dbgstore.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    var harga1: String = "",
    var harga2: String = "",
    var harga3: String = "",
    var jumlah1: String = "",
    var jumlah2: String = "",
    var jumlah3: String = "",
    var nama: String = "",
    var tipe: String = "",
    var url: String = ""
) : Parcelable
