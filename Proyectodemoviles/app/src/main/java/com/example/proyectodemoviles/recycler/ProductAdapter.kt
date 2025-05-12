package com.example.proyectodemoviles.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.model.dto.ProductDto

class ProductAdapter(
    private var items: List<ProductDto>,
    private val onItemClick: (ProductDto) -> Unit
) : RecyclerView.Adapter<MyViewProduct>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewProduct {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return MyViewProduct(view)
    }

    override fun onBindViewHolder(holder: MyViewProduct, position: Int) {
        val producto = items[position]
        holder.nombre.text = producto.name
        holder.precio.text = "Precio: $${producto.price}"

        Glide.with(holder.itemView.context)
            .load(producto.imageUrl)
            .placeholder(R.drawable.broken_image)
            .into(holder.imagen)

        holder.itemView.setOnClickListener {
            onItemClick(producto)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<ProductDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}
