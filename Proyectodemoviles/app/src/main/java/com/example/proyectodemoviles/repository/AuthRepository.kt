package com.example.proyectodemoviles.repository

import com.example.proyectodemoviles.model.dto.JwtTokensDto
import com.example.proyectodemoviles.model.dto.LoginUserDto
import com.example.proyectodemoviles.util.RetrofitClient
import retrofit2.Response
// Clase encargada de gestionar las peticiones relacionadas con autenticación
class AuthRepository {

    // Método para realizar el login con email y contraseña
    private val authService = RetrofitClient.getAuthService()

    // Método para realizar el login con email y contraseña en segundo plano
    suspend fun login(email: String, password: String): Response<JwtTokensDto> {
        val loginDto = LoginUserDto(email, password)
        return authService.login(loginDto)
    }
}