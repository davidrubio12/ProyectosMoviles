package com.example.proyectodemoviles.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodemoviles.databinding.RowCartBinding
import com.example.proyectodemoviles.model.dto.CartItemDto
import com.example.proyectodemoviles.util.Cart


class CartAdapter(
    private val items: List<CartItemDto>,
    private val onDeleteClick: (CartItemDto) -> Unit,
    private val onSumarClick: (CartItemDto) -> Unit,
    private val onRestarClick: (CartItemDto) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItemDto) {
            binding.productoNombre.text = item.productName
            binding.productoCantidad.text = "Cantidad: ${item.quantity}"
            binding.productoPrecio.text = "Subtotal: $${item.subtotal}"

            binding.iconDelete.setOnClickListener { onDeleteClick(item) }
            binding.btnSumar.setOnClickListener { onSumarClick(item) }
            binding.btnRestar.setOnClickListener { onRestarClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = RowCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
