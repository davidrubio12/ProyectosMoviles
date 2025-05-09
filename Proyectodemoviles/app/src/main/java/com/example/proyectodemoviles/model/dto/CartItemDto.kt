package com.example.proyectodemoviles.model.dto

data class CartItemDto(
    val productId: Long,
    val productName: String,
    val imageUrl: String,
    val unitPrice: Double,
    val quantity: Int,
    val subtotal: Double
)