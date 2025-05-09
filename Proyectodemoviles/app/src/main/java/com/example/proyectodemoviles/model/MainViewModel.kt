package com.example.proyectodemoviles.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodemoviles.data.TokenManager
import com.example.proyectodemoviles.model.dto.ProductDto
import com.example.proyectodemoviles.util.Foto
import kotlinx.coroutines.launch

class MainViewModel(private val tokenManager: TokenManager) : ViewModel() {

    private val mainState = MainState()

    private val _productos = MutableLiveData<List<ProductDto>>()
    val productos: LiveData<List<ProductDto>> get() = _productos

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun cargarProductos(search: String = "", categoriaId: Long? = null) {
        viewModelScope.launch {
            val token = tokenManager.getAccessToken()
            if (token != null) {
                val resultado = mainState.getProducts(search, categoriaId, token)
                _productos.value = resultado
            } else {
                _error.value = "Token no disponible"
            }
        }
    }
}