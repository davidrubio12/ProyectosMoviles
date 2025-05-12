package com.example.proyectodemoviles.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectodemoviles.R
import com.example.proyectodemoviles.databinding.RowBinding
import com.example.proyectodemoviles.model.dto.ProductDto

class MyAdapter(
    private var dataSet: List<ProductDto>,
    private val onItemClick: (ProductDto) -> Unit
) : RecyclerView.Adapter<MyAdapter.ProductViewHolder>() {

    fun updateData(newList: List<ProductDto>) {
        dataSet = newList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(private val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductDto) {
            binding.productoNombre.text = product.name
            binding.productoPrecio.text = "Precio: $${product.price}"
            Glide.with(binding.root.context)
                .load(product.imageUrl)
                .placeholder(R.drawable.broken_image)
                .into(binding.imV1)

            binding.root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}