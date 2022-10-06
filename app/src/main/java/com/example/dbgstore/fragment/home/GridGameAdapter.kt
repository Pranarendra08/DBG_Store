package com.example.dbgstore.fragment.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dbgstore.R
import com.example.dbgstore.data.Games

class GridGameAdapter internal constructor(private val listGame: ArrayList<Games>): RecyclerView.Adapter<GridGameAdapter.GridViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    internal fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridGameAdapter.GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_game, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridGameAdapter.GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listGame[position].photo)
            .into(holder.imgPhoto)

        holder.tvGameName.text = listGame[position].name

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listGame[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listGame.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_grid_game)
        var tvGameName: TextView = itemView.findViewById(R.id.tv_game_name)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Games)
    }
}