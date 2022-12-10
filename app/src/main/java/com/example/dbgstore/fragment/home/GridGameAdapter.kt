package com.example.dbgstore.fragment.home


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dbgstore.R
import com.example.dbgstore.data.Games

class GridGameAdapter (private val listGame: ArrayList<Games>,
                       private val listener:(Games) -> Unit)
    : RecyclerView.Adapter<GridGameAdapter.GridViewHolder>() {

    lateinit var contextAdapter: Context

//    private lateinit var onItemClickCallback: OnItemClickCallback
//
//    internal fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridGameAdapter.GridViewHolder {
        contextAdapter = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_game, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridGameAdapter.GridViewHolder, position: Int) {
//        Glide.with(holder.itemView.context)
//            .load(listGame[position].url)
//            .into(holder.imgPhoto)
//
//        holder.tvGameName.text = listGame[position].name
//
//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onItemClicked(listGame[holder.adapterPosition])
//        }
        holder.bindItem(listGame[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = listGame.size

    class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item_grid_game)
        var tvGameName: TextView = itemView.findViewById(R.id.tv_game_name)

        fun bindItem(games: Games, listener: (Games) -> Unit, contextAdapter: Context) {
            tvGameName.text = games.nama

            Glide.with(contextAdapter)
                .load(games.url)
                .into(imgPhoto)

            itemView.setOnClickListener {
                listener(games)
            }
        }
    }

//    interface OnItemClickCallback {
//        fun onItemClicked(data: Games)
//    }
}