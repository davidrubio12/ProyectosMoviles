package com.example.proyectodemoviles.model

import com.example.proyectodemoviles.model.dto.CartDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface CartApiService {
    @GET("cart")
    suspend fun getCart(): Response<CartDto>

    @DELETE("cart/{productId}")
    suspend fun deleteItemFromCart(@Path("productId") productId: Long): Response<CartDto>

    @POST("cart/{productId}/{count}")
    suspend fun addProductToCart(
        @Path("productId") productId: Long,
        @Path("count") count: Int
    ): Response<CartDto>
}