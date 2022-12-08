package com.example.dbgstore.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Games(
    var nama: String = "",
    var tipe: String = "",
    var url: String = ""
) : Parcelable
