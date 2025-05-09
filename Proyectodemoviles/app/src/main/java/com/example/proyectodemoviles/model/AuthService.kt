package com.example.proyectodemoviles.model

import com.example.proyectodemoviles.model.dto.JwtTokensDto
import com.example.proyectodemoviles.model.dto.LoginUserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body loginUserDto: LoginUserDto): Response<JwtTokensDto>
}