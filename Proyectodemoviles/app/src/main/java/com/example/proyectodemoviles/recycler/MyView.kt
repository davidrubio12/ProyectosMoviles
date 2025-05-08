package com.example.proyectodemoviles.recycler

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.R


class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imV1 = itemView.findViewById<View>(R.id.imV1) as ImageView
}