package com.example.proyectodemoviles.data.api

import com.example.proyectodemoviles.model.dto.CartDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApiService {
    @GET("cart")
    suspend fun getCart(): Response<CartDto>

    @POST("cart/{productId}")
    suspend fun addOneProductToCart(
        @Path("productId") productId: Long
    ): Response<CartDto>

    @POST("cart/{productId}/{quantity}")
    suspend fun addProductToCart(
        @Path("productId") productId: Long,
        @Path("quantity") quantity: Int
    ): Response<CartDto>

    @DELETE("cart/{productId}")
    suspend fun removeProductFromCart(
        @Path("productId") productId: Long
    ): Response<CartDto>

    @DELETE("cart")
    suspend fun clearCart(): Response<CartDto>
}
