package com.example.recyclerview.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class MyView(itemView: View) : RecyclerView.ViewHolder(itemView){ // Extend RecyclerView.ViewHolder{

    val tvAnimales : TextView = itemView.findViewById(R.id.tv1)

}
