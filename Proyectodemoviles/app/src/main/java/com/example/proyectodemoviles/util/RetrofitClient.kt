package com.example.proyectodemoviles.util


import com.example.proyectodemoviles.data.api.AuthService
import com.example.proyectodemoviles.data.api.CartApiService
import com.example.proyectodemoviles.data.api.ProductApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://10.0.2.2:8000/home/app/api/app/v1/"

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                SessionManager.accessToken?.let { token ->
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                chain.proceed(requestBuilder.build())
            }
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAuthService(): AuthService = retrofit.create(AuthService::class.java)
    fun getProductService(): ProductApiService = retrofit.create(ProductApiService::class.java)
    fun getCartService(): CartApiService = retrofit.create(CartApiService::class.java)
}
