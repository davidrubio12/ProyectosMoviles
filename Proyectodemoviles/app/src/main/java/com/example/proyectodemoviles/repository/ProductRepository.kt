package com.example.proyectodemoviles.repository

import com.example.proyectodemoviles.model.PageResponse
import com.example.proyectodemoviles.model.dto.ProductDto
import com.example.proyectodemoviles.util.RetrofitClient
import retrofit2.Response

class ProductRepository {

    private val api = RetrofitClient.getProductService()

    suspend fun getAllProducts(
        search: String = "",
        categoryId: Long? = null,
        page: Int = 0,
        size: Int = 100,
        sortBy: String = "name",
        sortDir: String = "asc"
    ): Response<PageResponse<ProductDto>> {
        return api.getProducts(
            search = search,
            categoryId = categoryId,
            page = page,
            size = size,
            sortBy = sortBy,
            sortDir = sortDir
        )
    }
}