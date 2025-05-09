package com.example.proyectodemoviles.model.dto

data class ProductDto(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)