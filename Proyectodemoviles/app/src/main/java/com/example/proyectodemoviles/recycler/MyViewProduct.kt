package com.example.proyectodemoviles.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.R

class MyViewProduct(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nombre: TextView = itemView.findViewById(R.id.productoNombre)
    val precio: TextView = itemView.findViewById(R.id.productoPrecio)
    val imagen: ImageView = itemView.findViewById(R.id.imV1)
}
