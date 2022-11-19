package com.example.dbgstore.sign.signin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    var email:String ?="",
    var nama:String ?="",
    var password:String ?="",
    var url:String ?="",
    var username:String ?=""
) : Parcelable