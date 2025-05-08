package com.example.proyectodemoviles.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.databinding.RowCartBinding
import com.example.proyectodemoviles.util.Cart


class CartAdapter(private val carritoList: MutableList<Cart>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = RowCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val producto = carritoList[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int {
        return carritoList.size
    }

    inner class CartViewHolder(private val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: Cart) {
            binding.productoNombre.text = producto.nombre
            binding.productoCantidad.text = "Cantidad: ${producto.cantidad}"
            binding.productoPrecio.text = "Precio: $${producto.precio}"
        }
    }
}
