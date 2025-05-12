package com.example.proyectodemoviles.model.dto

data class CartItemDto(
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val imageUrl: String? = null,
    val unitPrice: Double,
    val subtotal : Double
)