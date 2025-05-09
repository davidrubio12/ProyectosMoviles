package com.example.proyectodemoviles.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectodemoviles.data.TokenManager
import com.example.proyectodemoviles.model.dto.CartDto
import kotlinx.coroutines.launch

class CartViewModel(private val tokenManager: TokenManager) : ViewModel() {
    private val mainState = MainState()

    private val _cart = MutableLiveData<CartDto>()
    val cart: LiveData<CartDto> get() = _cart

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun cargarCarrito() {
        viewModelScope.launch {
            val token = tokenManager.getAccessToken()
            if (token != null) {
                try {
                    val result = mainState.getCartReal(token)
                    if (result != null) {
                        _cart.value = result
                    } else {
                        _error.value = "Error al obtener el carrito"
                    }
                } catch (e: Exception) {
                    _error.value = "Error: ${e.message}"
                }
            } else {
                _error.value = "Token no encontrado"
            }
        }
    }
    fun eliminarItem(productId: Long) {
        viewModelScope.launch {
            val token = tokenManager.getAccessToken()
            if (token != null) {
                try {
                    val result = mainState.deleteCartItem(productId, token)
                    if (result != null) {
                        _cart.value = result
                    } else {
                        _error.value = "Error al eliminar el producto del carrito"
                    }
                } catch (e: Exception) {
                    _error.value = "Excepción al eliminar: ${e.message}"
                }
            } else {
                _error.value = "Token no encontrado"
            }
        }
    }
    fun modificarCantidad(productId: Long, nuevaCantidad: Int) {
        viewModelScope.launch {
            val token = tokenManager.getAccessToken()
            if (token != null) {
                val result = mainState.updateCartQuantity(productId, nuevaCantidad, token)
                if (result != null) {
                    _cart.value = result
                } else {
                    _error.value = "Error al actualizar cantidad"
                }
            } else {
                _error.value = "Token no disponible"
            }
        }
    }

}