package com.example.ut4_recyclerview.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ut4_recyclerview.Color
import com.example.ut4_recyclerview.R

class MyAdapter(private val dataSet: List<Color>) : RecyclerView.Adapter<MyView>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.linea, parent, false)
        return MyView(view)

    }

    override fun getItemCount() = dataSet.size


    override fun onBindViewHolder(holder: MyView, position: Int) {
        val color = dataSet[position]
        holder.tvNombreColor.text = color.nombre
        holder.tvCodigoHex.text = color.codigoHex
        holder.itemView.setBackgroundColor(android.graphics.Color.parseColor(color.codigoHex))

        holder.itemView.setOnClickListener {
            holder.tvNombreColor.setTextColor(android.graphics.Color.parseColor(color.codigoHex))
            holder.tvCodigoHex.setTextColor(android.graphics.Color.parseColor(color.codigoHex))
            holder.itemView.setBackgroundColor(android.graphics.Color.WHITE)
        }

        holder.itemView.setOnLongClickListener{
            holder.tvNombreColor.setTextColor(android.graphics.Color.WHITE)
            holder.tvCodigoHex.setTextColor(android.graphics.Color.WHITE)
            holder.itemView.setBackgroundColor(android.graphics.Color.parseColor(color.codigoHex))
            true
        }
    }
}