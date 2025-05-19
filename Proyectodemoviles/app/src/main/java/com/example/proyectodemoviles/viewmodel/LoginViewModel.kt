package com.example.proyectodemoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodemoviles.model.dto.JwtTokensDto
import com.example.proyectodemoviles.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){

    private val authRepository = AuthRepository()

    private val _tokens = MutableLiveData<JwtTokensDto?>()
    val tokens: LiveData<JwtTokensDto?> get() = _tokens


    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.login(email, password)
                _tokens.value = if (response.isSuccessful) response.body() else null
            } catch (e: Exception) {
                _tokens.value = null
            }

        }
    }
}