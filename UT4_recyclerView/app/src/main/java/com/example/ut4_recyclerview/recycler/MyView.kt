package com.example.ut4_recyclerview.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ut4_recyclerview.R

class MyView(itemView: View) : RecyclerView.ViewHolder(itemView){ // Extend RecyclerView.ViewHolder{
    val tvNombreColor: TextView = itemView.findViewById(R.id.tvNombreColor)
    val tvCodigoHex: TextView = itemView.findViewById(R.id.tvCodigoHex)

}