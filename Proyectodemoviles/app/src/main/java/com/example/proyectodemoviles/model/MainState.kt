package com.example.proyectodemoviles.model

import com.example.proyectodemoviles.model.dto.CartDto
import com.example.proyectodemoviles.model.dto.JwtTokensDto
import com.example.proyectodemoviles.model.dto.LoginUserDto
import com.example.proyectodemoviles.model.dto.ProductDto
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainState {

    private val BASE_URL = "http://10.0.2.2:8000/api/app/v1/"

    // Retrofit sin token, para login y registro
    private val retrofitNoAuth = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun login(email: String, password: String): JwtTokensDto? {
        val authService = retrofitNoAuth.create(AuthService::class.java)
        val dto = LoginUserDto(email, password)
        val response = authService.login(dto)
        return if (response.isSuccessful) response.body() else null
    }

    // Retrofit con token JWT
    private fun getRetrofitWithToken(token: String): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getProducts(
        search: String,
        categoryId: Long?,
        token: String
    ): List<ProductDto> {
        val retrofit = getRetrofitWithToken(token)
        val service = retrofit.create(ProductAPIService::class.java)
        val response = service.getProducts(
            search = search,
            categoryId = categoryId
        )

        return if (response.isSuccessful) {
            response.body()?.content ?: emptyList()
        } else {
            println("Error: ${response.code()} ${response.message()}")
            emptyList()
        }
    }

    suspend fun getCartReal(token: String): CartDto? {
        val retrofit = getRetrofitWithToken(token)
        val service = retrofit.create(CartApiService::class.java)
        val response = service.getCart()
        return if (response.isSuccessful) response.body() else null
    }
    suspend fun deleteCartItem(productId: Long, token: String): CartDto? {
        val retrofit = getRetrofitWithToken(token)
        val service = retrofit.create(CartApiService::class.java)
        val response = service.deleteItemFromCart(productId)
        return if (response.isSuccessful) response.body() else null
    }
    suspend fun updateCartQuantity(productId: Long, quantity: Int, token: String): CartDto? {
        val retrofit = getRetrofitWithToken(token)
        val service = retrofit.create(CartApiService::class.java)
        val response = service.addProductToCart(productId, quantity)
        return if (response.isSuccessful) response.body() else null
    }
}