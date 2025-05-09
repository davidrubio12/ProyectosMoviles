package com.example.proyectodemoviles.model.dto

data class CartDto(
    val items: List<CartItemDto>,
    val totalQuantity: Int,
    val totalPrice: Double
)