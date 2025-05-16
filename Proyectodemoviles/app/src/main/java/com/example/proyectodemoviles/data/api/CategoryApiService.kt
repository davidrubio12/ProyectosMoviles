package com.example.proyectodemoviles.data.api

import com.example.proyectodemoviles.model.dto.CategoryDto
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiService {
    @GET("categories")
    suspend fun getCategories(): Response<List<CategoryDto>>
}