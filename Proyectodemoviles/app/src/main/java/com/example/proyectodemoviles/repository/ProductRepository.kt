package com.example.proyectodemoviles.repository


import com.example.proyectodemoviles.model.PageResponse
import com.example.proyectodemoviles.model.dto.ProductDto
import com.example.proyectodemoviles.util.RetrofitClient
import retrofit2.Response

class ProductRepository {

    private val api = RetrofitClient.getProductService()

    suspend fun getProductosPaginados(
        page: Int,
        search: String = "",
        categoriaId: Long? = null
    ): Response<PageResponse<ProductDto>> {
        return api.getProducts(
            search = search,
            categoryId = categoriaId,
            page = page,
            size = 10
        )
    }
}