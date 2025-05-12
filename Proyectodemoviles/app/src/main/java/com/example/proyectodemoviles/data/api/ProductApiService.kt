package com.example.proyectodemoviles.data.api

import com.example.proyectodemoviles.model.PageResponse
import com.example.proyectodemoviles.model.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {


    @GET("products/find")
    suspend fun getProducts(
        @Query("search") search: String = "",
        @Query("cat") categoryId: Long? = null,
        @Query("pageNumber") page: Int = 0,
        @Query("pageSize") size: Int = 20,
        @Query("sortBy") sortBy: String = "name",
        @Query("sortDir") sortDir: String = "asc"
    ): Response<PageResponse<ProductDto>>

    
}