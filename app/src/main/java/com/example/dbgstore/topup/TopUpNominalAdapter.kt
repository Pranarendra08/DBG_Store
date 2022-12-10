package com.example.dbgstore.topup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dbgstore.R
import com.example.dbgstore.data.Nominal

class TopUpNominalAdapter(private val listNominal: ArrayList<Nominal>,
                          private val listener: (Nominal) -> Unit)
    : RecyclerView.Adapter<TopUpNominalAdapter.NominalViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopUpNominalAdapter.NominalViewHolder {
        contextAdapter = parent.context
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_topup_nominal, parent, false)
        return NominalViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopUpNominalAdapter.NominalViewHolder, position: Int) {
        holder.bindItem(listNominal[position], listener, contextAdapter)
    }

    override fun getItemCount(): Int = listNominal.size

    class NominalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvHarga: TextView = itemView.findViewById(R.id.tv_harga)
        var tvJumlah: TextView = itemView.findViewById(R.id.tv_jumlah)

        fun bindItem(nominal: Nominal, listener: (Nominal) -> Unit, contextAdapter: Context) {
            tvHarga.text = nominal.harga
            tvJumlah.text = nominal.jumlah

            itemView.setOnClickListener {
                listener(nominal)
            }
        }
    }
}