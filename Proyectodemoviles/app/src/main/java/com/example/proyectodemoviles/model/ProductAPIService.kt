package com.example.proyectodemoviles.model
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPIService {

    @GET("images")
    suspend fun getProductos(): Response<ProductRespuesta>

    @GET("breed/{raza}/images")
    suspend fun getProductos(@Path("raza") raza: String): Response<ProductRespuesta>

}