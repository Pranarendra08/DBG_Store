package com.example.dbgstore.data

import com.example.dbgstore.R

object GameData {
    private val gameNames = arrayOf("Mobile Legend",
        "Valorant",
        "Call Of Duty",
        "Free Fire"
    )

    private val gameImages = intArrayOf(
        R.drawable.mobile_legend,
        R.drawable.valorant,
        R.drawable.call_of_duty,
        R.drawable.free_fire
    )

    val listData: ArrayList<Games>
        get() {
            val list = arrayListOf<Games>()
            for (position in gameNames.indices) {
                val games = Games()
                games.name = gameNames[position]
                games.photo = gameImages[position]
                list.add(games)
            }
            return list
        }
}