package com.example.proyectodemoviles.repository

import com.example.proyectodemoviles.model.dto.CartDto
import com.example.proyectodemoviles.util.RetrofitClient
import retrofit2.Response

class CartRepository {
    private val cartService = RetrofitClient.getCartService()

    suspend fun getCart(): Response<CartDto> = cartService.getCart()

    suspend fun addToCart(productId: Long, quantity: Int): Response<CartDto> {
        return if (quantity == 1) {
            cartService.addOneProductToCart(productId)
        } else {
            cartService.addProductToCart(productId, quantity)
        }
    }

    suspend fun removeFromCart(productId: Long): Response<CartDto> =
        cartService.removeProductFromCart(productId)
}