package com.example.proyectodemoviles.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodemoviles.model.dto.JwtTokensDto
import com.example.proyectodemoviles.model.dto.ProductDto
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){

    private val mainState = MainState()

    private val _tokens = MutableLiveData<JwtTokensDto>()
    val tokens: LiveData<JwtTokensDto> get() = _tokens


    fun login(email: String, password: String){
        viewModelScope.launch {

                _tokens.value = mainState.login(email, password)



        }
    }
}