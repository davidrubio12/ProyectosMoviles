package com.example.proyectodemoviles.model

import com.example.proyectodemoviles.model.dto.JwtTokensDto
import com.example.proyectodemoviles.model.dto.LoginUserDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainState {


    suspend fun recuperaFotos(raza:String): ProductRespuesta {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call = retrofit.create(ProductAPIService::class.java).getProductos(raza)
        val fotosPerros = call.body()
        return fotosPerros ?: ProductRespuesta("no success", null)
    }

    suspend fun login(username: String, password: String): JwtTokensDto? {

        // Crear el objeto LoginUserDto
        val loginUserDto = LoginUserDto(username, password)

        // Configurar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/home/app/") // Reemplaza con tu URL base
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Crear el servicio
        val authService = retrofit.create(AuthService::class.java)

        // Hacer la solicitud
        val response = authService.login(loginUserDto)

        // Retornar el JwtTokensDto si la respuesta es exitosa, sino retornar null
        return if (response.isSuccessful) {
            response.body()
        } else {
            println("Error en la respuesta: ${response.code()} ${response.message()}")
            null
        }
    }
}