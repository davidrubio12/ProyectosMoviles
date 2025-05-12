package com.example.proyectodemoviles.repository

import com.example.proyectodemoviles.model.dto.JwtTokensDto
import com.example.proyectodemoviles.model.dto.LoginUserDto
import com.example.proyectodemoviles.util.RetrofitClient
import retrofit2.Response

class AuthRepository {

    private val authService = RetrofitClient.getAuthService()

    suspend fun login(email: String, password: String): Response<JwtTokensDto> {
        val loginDto = LoginUserDto(email, password)
        return authService.login(loginDto)
    }
}