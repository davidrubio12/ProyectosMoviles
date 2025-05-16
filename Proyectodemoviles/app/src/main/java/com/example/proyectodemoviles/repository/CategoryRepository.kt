package com.example.proyectodemoviles.repository

import com.example.proyectodemoviles.model.dto.CategoryDto
import com.example.proyectodemoviles.util.RetrofitClient
import retrofit2.Response

class CategoryRepository {

 private val api = RetrofitClient.getCategoryService()


    suspend fun getAllCategories(): Response<List<CategoryDto>> {
        return api.getCategories()
    }


}