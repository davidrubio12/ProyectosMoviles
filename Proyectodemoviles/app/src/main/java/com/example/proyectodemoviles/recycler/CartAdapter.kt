package com.example.proyectodemoviles.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.databinding.RowCartBinding
import com.example.proyectodemoviles.model.dto.CartItemDto
import com.example.proyectodemoviles.model.Cart


class CartAdapter(
    private var items: List<CartItemDto>,
    private val onSumar: (CartItemDto) -> Unit,
    private val onRestar: (CartItemDto) -> Unit,
    private val onEliminar: (CartItemDto) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: RowCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItemDto) {
            binding.productoNombre.text = item.productName
            binding.productoCantidad.text = item.quantity.toString()
            binding.productoPrecio.text = "€ %.2f".format(item.unitPrice)
            binding.productoSubtotal.text = "Subtotal: € %.2f".format(item.subtotal)

            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.broken_image)
                .into(binding.productoImagen)

            binding.btnSumar.setOnClickListener {
                onSumar(item)
            }

            binding.btnRestar.setOnClickListener {
                if (item.quantity > 1) {
                    onRestar(item)
                } else {
                    Toast.makeText(binding.root.context, "Cantidad mínima: 1", Toast.LENGTH_SHORT).show()
                }
            }

            binding.iconDelete.setOnClickListener {
                onEliminar(item)
            }
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

    fun updateData(newItems: List<CartItemDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}
