package com.example.dbgstore.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nominal(
    var harga: String = "",
//    var nominal: String = "",
    var jumlah: String = ""
) : Parcelable