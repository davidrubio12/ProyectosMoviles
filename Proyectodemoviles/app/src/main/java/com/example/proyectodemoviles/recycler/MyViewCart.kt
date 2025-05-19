package com.example.proyectodemoviles.recycler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.R


// Define la clase que representa cada item en el recyclerView
class MyViewCart(itemView: View)  : RecyclerView.ViewHolder(itemView) {

    val productoImagen: ImageView = itemView.findViewById(R.id.productoImagen)
    val productoNombre: TextView = itemView.findViewById(R.id.productoNombre)
    val productoPrecio: TextView = itemView.findViewById(R.id.productoPrecio)
    val productoSubtotal: TextView = itemView.findViewById(R.id.productoSubtotal)
    val productoCantidad: TextView = itemView.findViewById(R.id.productoCantidad)

}